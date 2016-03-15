package io.async.converters.twitter.rxjava

import com.twitter.util.{Future, Promise}
import rx.{Observable, SingleSubscriber}

import scala.language.implicitConversions

/**
 * Conversion utilities for converting Twitter Util datatypes
 * into RxJava datatypes.
 */
object Rx2FutureConverters {

  object Single {
    def toFuture[A](single: rx.Single[A]): Future[A] =
      new ToFuture[A](single).toFuture
  }

  class ToFuture[A](val single: rx.Single[A]) {
    def toFuture: Future[A] = singleAsFuture(single)
  }

  @inline
  implicit def fromSingle[A](single: rx.Single[A]): ToFuture[A] =
    new ToFuture[A](single)


  @inline
  implicit def fromObservable[A](observable: Observable[A]): ToFuture[A] =
    fromSingle(observable.toSingle)

  private[this] def singleAsFuture[A](single: rx.Single[A]): Future[A] =
    new Promise[A] {
      private[this] val subscription = single.subscribe(new SingleSubscriber[A] {
        override def onError(error: Throwable): Unit = parent.setException(error)

        override def onSuccess(value: A): Unit = parent.setValue(value)
      })

      override def cancel(): Unit =
        subscription.unsubscribe()

    }

}
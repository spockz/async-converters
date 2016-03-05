package io.async.converters.twitter.rxjava

import com.twitter.util.{Future, Promise}
import rx.{Observable, Single, SingleSubscriber}

import scala.language.implicitConversions

object FutureConverters {

  class ToFuture[A](val single: Single[A]) extends AnyVal {
    def toFuture: Future[A] = singleAsFuture(single)
  }

  @inline
  implicit def toFuture[A](single: Single[A]): ToFuture[A] =
    new ToFuture[A](single)


  @inline
  implicit def toFuture[A](observable: Observable[A]): ToFuture[A] =
    toFuture(observable.toSingle)

  private def singleAsFuture[A](single: Single[A]): Future[A] =
    new Promise[A] {
      private val subscription = single.subscribe(new SingleSubscriber[A] {
        override def onError(error: Throwable): Unit = parent.setException(error)

        override def onSuccess(value: A): Unit = parent.setValue(value)
      })

      override def cancel(): Unit =
        subscription.unsubscribe()

    }

}
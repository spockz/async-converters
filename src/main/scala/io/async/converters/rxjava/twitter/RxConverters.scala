package io.async.converters.rxjava.twitter

import com.twitter.util._
import rx.{Observable, Single, SingleSubscriber}

import scala.language.implicitConversions

/**
 * Created by alessandro on 03/03/16.
 */
object RxConverters {

  class ToRx[A](val future: Future[A]) extends AnyVal {
    def toSingle: Single[A] = Observable.from[A](future.toJavaFuture).toSingle

    def toObservable: Observable[A] = toSingle.toObservable
  }

  implicit def futureAsRxOperations[A](future: Future[A]): ToRx[A] =
    new ToRx[A](future) // The `[A]` in `from[A]` is neccessary for the compiler.

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

//  def toVar[A](observable: Observable[A]) : Var[Try[A]] with Cancellable = {
//    new ReadWriteVar[Try[A]](Pending: Try[A]) with Cancellable {
//      protected final def parent: Updatable[Try[A]] = this
//
//      val subscription = observable.subscribe(new Subscriber[A]() {
//        override def onError(e: Throwable): Unit =
//          parent.update(Throw(e))
//
//        override def onCompleted(): Unit = parent.finalize()
//
//        override def onNext(t: A): Unit = parent.update(Return(t))
//      })
//
//      override def isCancelled: Boolean = ??? //track ourselves?
//
//      override def cancel(): Unit = subscription.unsubscribe()
//
//      override def linkTo(other: Cancellable): Unit = ??? // how to build this?
//    }
//
//
//  }
//
//  object Pending extends Nothing

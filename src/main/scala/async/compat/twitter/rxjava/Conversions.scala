package async.compat.twitter.rxjava

import com.twitter.util._
import rx.{Observable, Single, SingleSubscriber}

/**
 * Created by alessandro on 03/03/16.
 */
object Conversions {
  def toFuture[A](singleObservable: Single[A]): Future[A] =
    new Promise[A] {
      private val subscription = singleObservable.subscribe(new SingleSubscriber[A] {
        override def onError(error: Throwable): Unit = parent.setException(error)

        override def onSuccess(value: A): Unit = parent.setValue(value)
      })

      override def cancel(): Unit =
        subscription.unsubscribe()
    }


  @inline
  def toFuture[A](observable: Observable[A]): Future[A] =
    toFuture(observable.toSingle())


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

}

package async.compat.rxjava.twitter

import com.twitter.util.Future
import rx.Single.OnSubscribe
import rx.subjects.Subject
import rx.{SingleSubscriber, Single, Observable}

/**
 * Created by alessandro on 03/03/16.
 */
object Conversions {
  def toSingle[A](future: Future[A]): Single[A] =
    toObservable(future).toSingle

  def toObservable[A](future: Future[A]) : Observable[A] =
    Observable.from(future.toJavaFuture)

}

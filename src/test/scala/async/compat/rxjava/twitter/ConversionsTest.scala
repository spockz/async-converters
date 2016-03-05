package async.compat.rxjava.twitter

import com.twitter.util.{MockTimer, Future, Await, Duration}
import org.scalatest.{Matchers, FlatSpec}
import rx.Observable
import rx.functions.Action1

/**
 * Created by alessandro on 03/03/16.
 */
class ConversionsTest extends FlatSpec with Matchers{
  behavior of "Twitter Util Future to RxJava Conversions"

  it should "convert constant Observables correctly" in {
    implicit val timer = new MockTimer

    val obj = new Object
    val twitterFuture = Future.value(obj).delayed(Duration.fromMilliseconds(1))

    var observedValue : Option[Object] = None

    val observable = Conversions.toObservable(twitterFuture)

    observable.doOnNext(new Action1[Object] {
      override def call(t: Object): Unit = observedValue = Some(t)
    })

    observedValue should be(None)

    timer.tick()

    observable.toSingle.toBlocking.value() should equal(obj)

  }
}

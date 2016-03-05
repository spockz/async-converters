package io.async.converters.rxjava.twitter

import com.twitter.util.{MockTimer, Future, Await, Duration}
import io.async.converters.twitter.rxjava.FutureConverters
import org.scalatest.{Matchers, FlatSpec}
import rx.Observable
import rx.functions.Action1
import RxConverters._
/**
 * Created by alessandro on 03/03/16.
 */
class FutureRxConvertersTest extends FlatSpec with Matchers{
  behavior of "Twitter Util Future to RxJava Conversions"

  it should "convert constant Observables correctly" in {
    implicit val timer = new MockTimer

    val obj = new Object
    val twitterFuture = Future.value(obj).delayed(Duration.fromMilliseconds(1))

    var observedValue : Option[Object] = None

    val observable = twitterFuture.toObservable

    observable.doOnNext(new Action1[Object] {
      override def call(t: Object): Unit = observedValue = Some(t)
    })

    observedValue should be(None)

    timer.tick()

    observable.toSingle.toBlocking.value() should equal(obj)

  }
}

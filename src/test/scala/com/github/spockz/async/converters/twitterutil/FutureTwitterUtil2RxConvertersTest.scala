package com.github.spockz.async.converters.twitterutil

import com.twitter.util.{Await, Duration, Future, MockTimer}
import org.scalatest.{FlatSpec, Matchers}
import rx.Observable
import rx.functions.Action1
import TwitterUtil2RxConverters._
import com.github.spockz.async.converters.rxjava.Rx2FutureConverters
/**
 * Created by alessandro on 03/03/16.
 */
class FutureTwitterUtil2RxConvertersTest extends FlatSpec with Matchers{
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

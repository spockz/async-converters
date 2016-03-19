package com.github.spockz.async.converters.rxjava

import com.twitter.util.{Duration, Await}
import org.scalatest.FlatSpec
import rx.Observable
import Rx2FutureConverters._

/**
 * Created by alessandro on 03/03/16.
 */
class ConversionsTest extends FlatSpec {
  behavior of "RxJava to Twitter Util Future Conversions"

  it should "convert constant Observables correctly" in {
    val obj = BigDecimal(5)
    val rxObservable = Observable.just(obj)


    val twitterFuture1 = rxObservable.toFuture //Conversions.toFuture(rxObservable)
    assert(Await.result(twitterFuture1) === obj, Duration.fromMilliseconds(5))

    val rxSingleObservable = rxObservable.toSingle

    val twitterFuture2 = rxSingleObservable.toFuture
    assert(Await.result(twitterFuture2) === obj, Duration.fromMilliseconds(5))
  }
}

package async.compat.twitter.rxjava


import com.twitter.util.{Duration, Await}
import org.scalatest.FlatSpec
import rx.Observable

/**
 * Created by alessandro on 03/03/16.
 */
class ConversionsTest extends FlatSpec {
  behavior of "RxJava to Twitter Util Future Conversions"

  it should "convert constant Observables correctly" in {
    val obj = BigDecimal(5)
    val rxObservable = Observable.just(obj)


    val twitterFuture1 = Conversions.toFuture(rxObservable)
    assert(Await.result(twitterFuture1) === obj, Duration.fromMilliseconds(5))

    val rxSingleObservable = rxObservable.toSingle

    val twitterFuture2 = Conversions.toFuture(rxSingleObservable)
    assert(Await.result(twitterFuture2) === obj, Duration.fromMilliseconds(5))
  }
}

package io.async.converters.rxjava.twitter

import com.twitter.util.{Try, Future}
import org.scalatest.FlatSpec
import rx.Observable

/**
 * Created by alessandro on 05/03/16.
 */
class CompileTest extends FlatSpec {
  behavior of "FutureConverters"

  it should "compile" in {

    import io.async.converters.twitter.rxjava.FutureConverters._
    import RxConverters._

    toFuture(Future.const(Try(new Object)).toObservable.toSingle)
    Future.const(Try(new Object)).toObservable.toSingle.toFuture
    Observable.just(new Object).toSingle.toFuture.toSingle
  }
}

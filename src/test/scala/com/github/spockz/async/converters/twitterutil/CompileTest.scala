package com.github.spockz.async.converters.twitterutil

import com.github.spockz.async.converters.rxjava.Rx2FutureConverters._
import com.twitter.util.{Future, Try}
import org.scalatest.FlatSpec
import rx.Observable

/**
 * Created by alessandro on 05/03/16.
 */
class CompileTest extends FlatSpec {
  behavior of "FutureConverters"

  it should "compile" in {

    import TwitterUtil2RxConverters._

    fromFuture(Future.const(Try(new Object))).toObservable.toSingle
    Future.const(Try(new Object)).toObservable.toSingle.toFuture
    Observable.just(new Object).toSingle.toFuture.toSingle
  }
}

package io.async.converters.rxjava.twitter

import com.twitter.util._
import rx.{Observable, Single, SingleSubscriber}

import scala.language.implicitConversions

/**
 * Conversion utilities for converting Twitter Util datatypes
 * into RxJava datatypes.
 */
object TwitterUtil2RxConverters {

  class ToRx[A](val future: Future[A]) extends AnyVal {
    def toSingle: Single[A] =
    // The `[A]` in `from[A]` is necessary for the compiler.
      Observable.from[A](future.toJavaFuture).toSingle

    def toObservable: Observable[A] = toSingle.toObservable
  }

  implicit def fromFuture[A](future: Future[A]): ToRx[A] =
    new ToRx[A](future)
}

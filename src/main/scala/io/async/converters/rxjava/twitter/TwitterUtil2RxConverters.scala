package io.async.converters.rxjava.twitter

import com.twitter.util._
import rx._

import scala.language.implicitConversions

/**
 * Conversion utilities for converting Twitter Util datatypes
 * into RxJava datatypes.
 */
object TwitterUtil2RxConverters {

  /**
   * Class containing methods for converting [[com.twitter.util.Future]]s to
   * RxJava values.
   * @param future the source [[com.twitter.util.Future]]
   * @tparam A the type of the future value
   */
  class FutureToRx[A](val future: Future[A]) extends AnyVal {

    /**
     * Convert the underlying future to a [[rx.Single]]
     */
    def toSingle: Single[A] =
    // The `[A]` in `from[A]` is necessary for the compiler.
      Observable.from[A](future.toJavaFuture).toSingle

    /**
     * Convert the underlying future into an [[rx.Observable]] with a single
     * value.
     */
    def toObservable: Observable[A] = toSingle.toObservable
  }

  implicit def fromFuture[A](future: Future[A]): FutureToRx[A] =
    new FutureToRx[A](future)
}

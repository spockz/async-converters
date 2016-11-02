package com.github.spockz.async.converters.twitterutil

import com.twitter.util._
import rx.Single.OnSubscribe
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
    def toSingle: Single[A] = Single.create[A](new Subscriber[A](future))

    /**
     * Convert the underlying future into an [[rx.Observable]] with a single
     * value.
     */
    def toObservable: Observable[A] = toSingle.toObservable
  }

  private class Subscriber[A](val future: Future[A]) extends OnSubscribe[A] {
    override def call(subscriber: SingleSubscriber[_ >: A]): Unit = {
      future.map { value =>
        if (!subscriber.isUnsubscribed) subscriber.onSuccess(value)
      }
      future.onFailure { throwable =>
        if (!subscriber.isUnsubscribed) subscriber.onError(throwable)
      }
    }
  }

  implicit def fromFuture[A](future: Future[A]): FutureToRx[A] =
    new FutureToRx[A](future)
}

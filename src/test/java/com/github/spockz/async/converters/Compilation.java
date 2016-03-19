package com.github.spockz.async.converters;

import com.github.spockz.async.converters.rxjava.Rx2FutureConverters;
import com.github.spockz.async.converters.twitterutil.TwitterUtil2RxConverters;
import com.twitter.util.Future;
import com.github.spockz.async.converters.rxjava.Rx2FutureConverters$;
import rx.Observable;

/**
 * Created by alessandro on 05/03/16.
 */
public class Compilation {
    public void compile() {
        final Observable<Object> observable = Observable.just(5);

        final Rx2FutureConverters.ToFuture<Object> convert = Rx2FutureConverters$.MODULE$.<Object>fromObservable(observable);

        convert.toFuture();
        Rx2FutureConverters.FromSingle$.MODULE$.toFuture(observable.toSingle());
        // This does actually compile when called from outside the project...
//        Rx2FutureConverters.FromSingle.toFuture(observable.toSingle());

        Rx2FutureConverters.fromSingle(observable.toSingle()).toFuture();
        new TwitterUtil2RxConverters.FutureToRx<Object>(Future.value(new Object())).toSingle();
    }
}

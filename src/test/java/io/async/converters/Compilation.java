package io.async.converters;

import com.twitter.util.Future;
import io.async.converters.rxjava.twitter.TwitterUtil2RxConverters;
import io.async.converters.twitter.rxjava.Rx2FutureConverters;
import io.async.converters.twitter.rxjava.Rx2FutureConverters$;
import rx.Observable;

/**
 * Created by alessandro on 05/03/16.
 */

public class Compilation {
    public void compile() {
        final Observable<Object> observable = Observable.just(5);
//        FutureConverters.toFuture(observable).toFuture();
        final Rx2FutureConverters.ToFuture<Object> convert = Rx2FutureConverters$.MODULE$.<Object>fromObservable(observable);

        convert.toFuture();
        Rx2FutureConverters.Single$.MODULE$.toFuture(observable.toSingle());

        Rx2FutureConverters.fromSingle(observable.toSingle()).toFuture();
        new TwitterUtil2RxConverters.ToRx<Object>(Future.value(new Object()));
    }
}

package io.async.converters;

import io.async.converters.twitter.rxjava.FutureConverters;
import rx.Observable;

/**
 * Created by alessandro on 05/03/16.
 */

public class Compilation {
    public void compile() {
        final Observable<Object> observable = Observable.just(5);
        FutureConverters.toFuture(observable).toFuture();
    }
}

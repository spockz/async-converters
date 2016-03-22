# async-converters
Providing conversion functions between several kind of Asynchronous datatypes. 

[![Join the chat at https://gitter.im/spockz/async-converters](https://badges.gitter.im/spockz/async-converters.svg)](https://gitter.im/spockz/async-converters?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge) [![Codacy Badge](https://api.codacy.com/project/badge/grade/6fe4148741334f5dae2dbf571462e741)](https://www.codacy.com/app/github_9/async-converters) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.spockz/async-converters_2.11/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.spockz/async-converters_2.11)

## Features

This project aims to provide easy to use conversion methods from a
representation of asynchronous values in one package to another package,
e.g. RxJava's `rx.Observable` to Twitter's `com.twitter.util.Future`.

## Structure

The packages are structured according to the following scheme: 
`com.github.spockz.async.converters.$sourceLibrary`.

## Examples

From Scala one can either using implicit conversion or static method
calls:

```scala
val obj = BigDecimal(5)
val rxObservable = Observable.just(obj)

import Rx2FutureConverters._
val twitterFuture1 = rxObservable.toFuture
// or
val twitterFuture2 =
  Rx2FutureConverters.FromSingle.toFuture(rxObservable.toSingle)
```

From Java
```java
final Observable<Object> observable = Observable.just(5);
final Future<Object> future = 
  Rx2FutureConverters.fromSingle(observable.toSingle()).toFuture();
```

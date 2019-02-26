package com.zimplifica.sitepay.libs.rx.transformers;


import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

public final class Transformers {
  private Transformers() {}

  public static <S, T> CombineLatestPairTransformer<S, T> combineLatestPair(final @NonNull Observable<T> second) {
    return new CombineLatestPairTransformer<>(second);
  }

  public static <S, T> TakeWhenTransformer<S, T> takeWhen(final @NonNull Observable<T> when) {
    return new TakeWhenTransformer<>(when);
  }
}

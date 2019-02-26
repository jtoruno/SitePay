package com.zimplifica.sitepay.libs.rx.transformers;

import android.util.Pair;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;


public final class CombineLatestPairTransformer<S, T> implements ObservableTransformer<S, Pair<S, T>> {
  @NonNull
  private final Observable<T> second;

  public CombineLatestPairTransformer(final @NonNull Observable<T> second) {
    this.second = second;
  }

  @Override
  public ObservableSource<Pair<S, T>> apply(Observable<S> first) {
    return Observable.combineLatest(first, this.second, Pair::new);
  }
}

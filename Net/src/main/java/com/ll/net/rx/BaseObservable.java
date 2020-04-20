package com.ll.net.rx;

import androidx.lifecycle.LifecycleOwner;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BaseObservable {

    public static <T> void doObservable(Observable<T> toObservable, BaseObserver<T> observer, LifecycleOwner owner){
        toObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .as(AutoDispose.<T>autoDisposable(AndroidLifecycleScopeProvider.from(owner)))
                .subscribe(observer);
    }
}

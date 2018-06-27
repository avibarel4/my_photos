package com.avi.myphotos.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by avi.barel on 27/06/2018.
 */
abstract class BaseViewModel : ViewModel() {

    protected var compositeDisposable: CompositeDisposable

    init {
        compositeDisposable = CompositeDisposable()
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.clear()
        compositeDisposable.dispose()
    }

}
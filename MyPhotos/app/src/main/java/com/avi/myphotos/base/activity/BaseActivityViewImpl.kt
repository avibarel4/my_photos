package com.avi.myphotos.base.activity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by avi.barel on 27/06/2018.
 */
abstract class BaseActivityViewImpl(context: Context, container: ViewGroup?) : IBaseActivityView {

    private var rootView: View

    init {
        rootView = LayoutInflater.from(context).inflate(this.layoutResId(), container)
    }

    final override fun getRootView(): View {
        return rootView
    }

}
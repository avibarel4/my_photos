package com.avi.myphotos.base.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avi.myphotos.base.fragment.IBaseFragmentView

/**
 * Created by avi.barel on 27/06/2018.
 */
abstract class BaseFragmentViewImpl(layoutInflater: LayoutInflater, container: ViewGroup?) : IBaseFragmentView {

    private var rootView: View

    init {
        if (layoutInflater == null) {
            throw RuntimeException("One must provide Layout Inflater")
        }

        rootView = layoutInflater.inflate(this.layoutResId(), container, false)
    }

    final override fun getRootView(): View {
        return rootView
    }

}
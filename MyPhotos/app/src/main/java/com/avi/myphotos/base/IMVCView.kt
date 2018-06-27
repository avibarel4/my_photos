package com.avi.myphotos.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.View

/**
 * Created by avi.barel on 27/06/2018.
 */
interface IMVCView {

    @LayoutRes
    fun layoutResId(): Int

    fun getRootView(): View

    fun getViewState(): Bundle?

    fun initViews(rootView: View)

    fun releaseViews()

}
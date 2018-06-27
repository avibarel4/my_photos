package com.avi.myphotos.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.avi.myphotos.R
import com.avi.myphotos.base.activity.BaseActivityViewImpl
import com.avi.myphotos.base.fragment.BaseFragment
import com.avi.myphotos.ui.photos.MainFragment

/**
 * Created by avi.barel on 27/06/2018.
 */
class MainActivityViewImpl(context: Context, container: ViewGroup?) : BaseActivityViewImpl(context, container), IMainActivityView {

    override fun layoutResId(): Int {
        return R.layout.activity_main
    }

    override fun getViewState(): Bundle? {
        return null
    }

    override fun initialFragment(): BaseFragment<*,*>? {
        return MainFragment()
    }

    override fun containerId(): Int {
        return R.id.container
    }

    override fun initViews(rootView: View) {

    }

    override fun releaseViews() {

    }

}
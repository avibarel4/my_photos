package com.avi.myphotos.base.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avi.myphotos.base.BaseViewModel

/**
 * Created by avi.barel on 27/06/2018.
 */
abstract class BaseFragment<VM : BaseViewModel, VI : BaseFragmentViewImpl> : Fragment() {

    protected lateinit var viewImpl: VI
    protected lateinit var viewModel: VM

    abstract fun handleBackPressed(): Boolean
    abstract fun getFragmentTag(): String

    protected abstract fun provideFragmentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): VI
    protected abstract fun provideViewModel(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(provideViewModel())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewImpl = provideFragmentView(inflater, container, savedInstanceState)
        val rootView = viewImpl.getRootView()
        viewImpl.initViews(rootView)
        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()

        viewImpl.releaseViews()
    }

}
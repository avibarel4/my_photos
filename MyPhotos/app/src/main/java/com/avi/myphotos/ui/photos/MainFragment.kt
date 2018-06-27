package com.avi.myphotos.ui.photos

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.avi.myphotos.base.fragment.BaseFragment
import com.avi.myphotos.ui.fullimage.FullImageFragment
import com.avi.myphotos.ui.main.MainActivity

/**
 * Created by avi.barel on 27/06/2018.
 */
class MainFragment : BaseFragment<MainFragmentViewModel, MainFragmentViewImpl>(), IMainFragmentView.MainFragmentCallbacks {

    override fun getFragmentTag(): String {
        return MainFragment::class.java.simpleName
    }

    override fun handleBackPressed(): Boolean {
        return false
    }

    override fun provideFragmentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): MainFragmentViewImpl {
        return MainFragmentViewImpl(inflater, container, savedInstanceState)
    }

    override fun provideViewModel(): Class<MainFragmentViewModel> {
        return MainFragmentViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.isLoading.observe(this, Observer {
            viewImpl.toggleProgress(it ?: false)
        })

        viewModel.photoItemSelected.observe(this, Observer {
            it?.link?.let { imageLink ->
                (activity as? MainActivity)?.let {
                    it.openOverlayFragment(FullImageFragment.newInstance(imageLink))
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()

        viewImpl.setListener(this)

        viewImpl.setAdapter(viewModel.getPhotosAdapter())
    }

    override fun onPause() {
        super.onPause()

        viewImpl.setListener(null)

        viewImpl.setAdapter(null)
    }

    override fun onSearchSubmit(searchTerm: String) {
        viewModel.submitSearch(searchTerm, true)
    }

}
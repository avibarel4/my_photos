package com.avi.myphotos.ui.fullimage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.avi.myphotos.base.fragment.BaseFragment

/**
 * Created by avi.barel on 27/06/2018.
 */
class FullImageFragment : BaseFragment<FullImageFragmentViewModel, FullImageFragmentViewImpl>() {

    private var imageUrl: String = ""

    companion object {

        private const val EXTRA_KEY_IMAGE_URL = "extra_key_image_url"

        fun newInstance(imageUrl: String): FullImageFragment {
            val args = Bundle()
            val fragment = FullImageFragment()
            args.putString(EXTRA_KEY_IMAGE_URL, imageUrl)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            imageUrl = it.getString(EXTRA_KEY_IMAGE_URL, "")
        }
    }

    override fun handleBackPressed(): Boolean {
        return false
    }

    override fun getFragmentTag(): String {
        return FullImageFragment::class.java.simpleName
    }

    override fun provideFragmentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): FullImageFragmentViewImpl {
        return FullImageFragmentViewImpl(inflater, container, imageUrl)
    }

    override fun provideViewModel(): Class<FullImageFragmentViewModel> {
        return FullImageFragmentViewModel::class.java
    }
}
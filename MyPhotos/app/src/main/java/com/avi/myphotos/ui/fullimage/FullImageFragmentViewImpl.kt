package com.avi.myphotos.ui.fullimage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.avi.myphotos.R
import com.avi.myphotos.base.fragment.BaseFragmentViewImpl
import com.bumptech.glide.Glide

/**
 * Created by avi.barel on 27/06/2018.
 */
class FullImageFragmentViewImpl(layoutInflater: LayoutInflater, container: ViewGroup?, private val imageUrl: String) : BaseFragmentViewImpl(layoutInflater, container), IFullImageFragmentView {

    private var image: ImageView? = null

    override fun layoutResId(): Int {
        return R.layout.fragmet_full_image
    }

    override fun getViewState(): Bundle? {
        return null
    }

    override fun initViews(rootView: View) {
        image = rootView.findViewById(R.id.image)

        Glide.with(rootView.context)
            .load(imageUrl)
            .placeholder(R.mipmap.ic_launcher)
            .fitCenter()
            .into(image)
    }

    override fun releaseViews() {
        image = null
    }

}
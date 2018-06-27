package com.avi.myphotos.ui.photos

import com.avi.myphotos.base.fragment.IBaseFragmentView

/**
 * Created by avi.barel on 27/06/2018.
 */
interface IMainFragmentView : IBaseFragmentView {

    interface MainFragmentCallbacks {
        fun onSearchSubmit(searchTerm: String)
    }

    fun setAdapter(photosAdapter: PhotosAdapter?)
    fun toggleProgress(show: Boolean)
    fun setListener(listener: MainFragmentCallbacks?)

}
package com.avi.myphotos.ui.photos

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import com.avi.myphotos.R
import com.avi.myphotos.base.App
import com.avi.myphotos.base.fragment.BaseFragmentViewImpl

/**
 * Created by avi.barel on 27/06/2018.
 */
class MainFragmentViewImpl(layoutInflater: LayoutInflater, container: ViewGroup?, val savedState: Bundle?) : BaseFragmentViewImpl(layoutInflater, container), IMainFragmentView, View.OnClickListener, TextView.OnEditorActionListener {

    private var progress: View? = null
    private var editSearch: EditText? = null
    private var btnSearch: View? = null
    private var recyclerPhotos: RecyclerView? = null

    private var photosItemDecoration: PhotosItemDecoration? = null

    private var listener: IMainFragmentView.MainFragmentCallbacks? = null

    override fun layoutResId(): Int {
        return R.layout.fragment_main
    }

    override fun getViewState(): Bundle? {
        return savedState
    }

    override fun initViews(rootView: View) {

        progress = rootView.findViewById(R.id.progress)

        editSearch = rootView.findViewById(R.id.edit_search)
        btnSearch = rootView.findViewById(R.id.btn_search)
        btnSearch?.setOnClickListener(this)

        editSearch?.setOnEditorActionListener(this)

        recyclerPhotos = rootView.findViewById(R.id.recycler_photos)

        val spanCount = rootView.resources.getInteger(R.integer.photos_grid_span_count)

        recyclerPhotos?.layoutManager = GridLayoutManager(rootView.context, spanCount, RecyclerView.VERTICAL, false)

        photosItemDecoration = PhotosItemDecoration(spanCount)
        recyclerPhotos?.addItemDecoration(photosItemDecoration)

    }

    override fun releaseViews() {

        progress = null

        editSearch?.setOnEditorActionListener(null)

        editSearch = null
        btnSearch?.setOnClickListener(null)
        btnSearch = null

        recyclerPhotos?.removeItemDecoration(photosItemDecoration)
        photosItemDecoration = null

        recyclerPhotos?.layoutManager = null
        recyclerPhotos?.adapter = null

        recyclerPhotos = null
    }

    override fun setAdapter(photosAdapter: PhotosAdapter?) {
        recyclerPhotos?.adapter = photosAdapter
    }

    override fun toggleProgress(show: Boolean) {
        progress?.visibility = if (show) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun onClick(v: View) {
        if (!App.isTouchEnabled()) {
            return
        }

        App.disableTouchEventForDefaultDuration()

        when (v.id) {
            R.id.btn_search -> {
                submitSearch()
            }
        }
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {

        if (!App.isTouchEnabled()) {
            return false
        }

        App.disableTouchEventForDefaultDuration()

        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            submitSearch()

            return true
        }

        return false
    }

    private fun submitSearch() {
        listener?.onSearchSubmit(editSearch?.text?.toString() ?: "")

        closeKeyboard()
    }

    private fun closeKeyboard() {
        // close keyboard after search
        editSearch?.let {
            it.post {
                val imm = App.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(it.windowToken, 0)
            }
        }
    }

    override fun setListener(listener: IMainFragmentView.MainFragmentCallbacks?) {
        this.listener = listener
    }

}
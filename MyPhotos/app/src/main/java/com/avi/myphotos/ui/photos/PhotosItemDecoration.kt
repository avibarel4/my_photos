package com.avi.myphotos.ui.photos

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.avi.myphotos.R
import com.avi.myphotos.base.App

/**
 * Created by avi.barel on 27/06/2018.
 */
class PhotosItemDecoration(private val spanCount: Int) : RecyclerView.ItemDecoration() {

    companion object {
        private val ITEM_MARGIN = App.context.resources.getDimensionPixelSize(R.dimen.photos_grid_margin)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount // item column

        outRect.left = ITEM_MARGIN - column * ITEM_MARGIN / spanCount
        outRect.right = (column + 1) * ITEM_MARGIN / spanCount

        if (position < spanCount) { // top edge
            outRect.top = ITEM_MARGIN
        }
        outRect.bottom = ITEM_MARGIN // item bottom

    }

}
package com.avi.myphotos.ui.photos

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.avi.myphotos.R
import com.avi.myphotos.base.App
import com.avi.myphotos.network.models.PhotoItem
import com.bumptech.glide.Glide

/**
 * Created by avi.barel on 27/06/2018.
 */
class PhotosAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), View.OnClickListener {

    companion object {
        private const val VIEW_TYPE_LOADER = 0
        private const val VIEW_TYPE_PHOTO = 1
    }

    data class PhotoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imgPhoto: ImageView = view.findViewById(R.id.img_photo)
        val txtPhotoName: TextView = view.findViewById(R.id.txt_photo_name)
    }

    data class LoaderViewHolder(val progressBar: ProgressBar) : RecyclerView.ViewHolder(progressBar)

    private val items: ArrayList<PhotoItem>

    private var listener: PhotosCallback? = null

    init {
        items = arrayListOf()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun addMoreItems(photos: List<PhotoItem>, hasMoreItems: Boolean) {

        val lastItem = getLastItem()
        val insertIndex = if (lastItem?.isDummyItem == true) {
            itemCount - 1
        } else {
            itemCount
        }

        items.addAll(insertIndex, photos)
        notifyItemRangeInserted(insertIndex, photos.size)

        if (hasMoreItems) {
            // show loader if doesn't shown already
            if (lastItem?.isDummyItem != true) {
                items.add(PhotoItem(isDummyItem = true))
                notifyItemInserted(itemCount - 1)
            }
        } else {
            // no more items.. remove loader
            if (lastItem?.isDummyItem == true) {
                items.removeAt(itemCount - 1)
                notifyItemRemoved(itemCount - 1)
            }
        }
    }

    fun setListener(listener: PhotosCallback?) {
        this.listener = listener
    }

    private fun getLastItem(): PhotoItem? {
        if (itemCount > 0) {
            return items[itemCount - 1]
        }
        return null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            VIEW_TYPE_PHOTO -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_photo, parent, false)
                return PhotoViewHolder(view)
            }
            VIEW_TYPE_LOADER -> {
                val progressBar = ProgressBar(parent.context)
                return LoaderViewHolder(progressBar)
            }
            else -> {
                throw Exception("Invalid Type")
            }
        }
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)

        if (holder.itemViewType == VIEW_TYPE_LOADER) {
            listener?.onLoaderAppeared()
        } else if (holder.itemViewType == VIEW_TYPE_PHOTO) {
            holder.itemView.setOnClickListener(this)
        }
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)

        if (holder.itemViewType == VIEW_TYPE_PHOTO) {
            holder.itemView.setOnClickListener(null)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val photoItem = items[position]

        when (holder) {
            is PhotoViewHolder -> {

                holder.itemView.tag = position

                Glide.with(holder.view.context)
                    .load(photoItem.image?.thumbnailLink ?: "")
                    .into(holder.imgPhoto)
                holder.txtPhotoName.text = photoItem.title
            }
            is LoaderViewHolder -> {

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return if (item.isDummyItem) {
            VIEW_TYPE_LOADER
        } else {
            VIEW_TYPE_PHOTO
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onClick(v: View) {
        if (!App.isTouchEnabled()) {
            return
        }

        App.disableTouchEventForDefaultDuration()

        (v.tag as? Int)?.let {
            val item = items[it]
            if (!item.isDummyItem) {
                listener?.onImageSelected(item)
            }
        }
    }

    interface PhotosCallback {
        fun onLoaderAppeared()
        fun onImageSelected(photoItem: PhotoItem)
    }

}
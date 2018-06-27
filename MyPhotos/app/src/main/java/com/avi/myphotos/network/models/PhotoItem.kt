package com.avi.myphotos.network.models

import com.google.gson.annotations.SerializedName

/**
 * Created by avi.barel on 27/06/2018.
 */
data class PhotoItem(
    @SerializedName("title") val title: String? = null,
    @SerializedName("link") val link: String? = null,
    @SerializedName("image") val image: Image? = null,
    val isDummyItem: Boolean = false
)
package com.avi.myphotos.network.models

import com.google.gson.annotations.SerializedName

/**
 * Created by avi.barel on 27/06/2018.
 */
data class Image(
    @SerializedName("thumbnailLink") val thumbnailLink: String?
)
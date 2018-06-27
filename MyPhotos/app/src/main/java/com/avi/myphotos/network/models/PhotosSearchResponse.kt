package com.avi.myphotos.network.models

import com.google.gson.annotations.SerializedName

/**
 * Created by avi.barel on 27/06/2018.
 */
data class PhotosSearchResponse(
    @SerializedName("queries") val queries: Queries?,
    @SerializedName("items") val items: List<PhotoItem>?
)
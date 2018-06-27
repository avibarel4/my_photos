package com.avi.myphotos.network.models

import com.google.gson.annotations.SerializedName

/**
 * Created by avi.barel on 27/06/2018.
 */
data class Queries(
    @SerializedName("previousPage") val previousPage: List<Query>?,
    @SerializedName("request") val request: List<Query>?,
    @SerializedName("nextPage") val nextPage: List<Query>?
) {
    data class Query(
        @SerializedName("totalResults") val totalResults: Long,
        @SerializedName("count") val count: Int,
        @SerializedName("startIndex") val startIndex: Int
    )
}
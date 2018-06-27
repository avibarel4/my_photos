package com.avi.myphotos.network

import com.avi.myphotos.network.models.PhotosSearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by avi.barel on 27/06/2018.
 */
interface PhotosService {

    @GET
    fun searchPhotos(@Url url: String): Single<PhotosSearchResponse>

}
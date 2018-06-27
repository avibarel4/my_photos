package com.avi.myphotos.network

import com.avi.myphotos.BuildConfig
import com.avi.myphotos.network.models.PhotosSearchResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by avi.barel on 27/06/2018.
 */
class PhotosManager private constructor() {

    companion object Singleton {
        val INSTANCE: PhotosManager by lazy { PhotosManager() }
    }

    private val photosService: PhotosService

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(loggingInterceptor)

        val retrofit = Retrofit.Builder()
            .callFactory(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(UrlFactory.PHOTOS_BASE_URL)
            .build()

        photosService = retrofit.create(PhotosService::class.java)
    }

    fun searchPhotos(pageSize: Int, currentPage: Int, query: String): Single<PhotosSearchResponse> {
        return photosService.searchPhotos(UrlFactory.getSearchUrl(pageSize, currentPage, query))
    }
}

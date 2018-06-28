package com.avi.myphotos.ui.photos

import android.arch.lifecycle.MutableLiveData
import com.avi.myphotos.base.BaseViewModel
import com.avi.myphotos.network.PhotosManager
import com.avi.myphotos.network.models.PhotoItem
import com.avi.myphotos.network.models.PhotosSearchResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by avi.barel on 27/06/2018.
 */
class MainFragmentViewModel : BaseViewModel(), PhotosAdapter.PhotosCallback {

    companion object {
        private const val RESULT_COUNT = 10
    }

    private val photosAdapter: PhotosAdapter

    init {
        photosAdapter = PhotosAdapter()
        photosAdapter.setListener(this)
    }

    override fun onCleared() {
        super.onCleared()

        photosAdapter.setListener(null)
    }

    val isLoading = MutableLiveData<Boolean>()
    val photoItemSelected = MutableLiveData<PhotoItem>()

    private var lastSearchTerm = ""
    private var nextPageStartIndex: Int? = 1
    private var noMoreItems = false

    fun submitSearch(searchTerm: String, newSearch: Boolean = false) {

        if (isLoading.value == true) {
            return // cancel as there's a pending request
        }

        compositeDisposable.clear() // stop previous calls

        if (newSearch || lastSearchTerm != searchTerm) {

            isLoading.value = true

            // New Search..

            noMoreItems = false

            lastSearchTerm = searchTerm

            // reset data
            nextPageStartIndex = 1
            photosAdapter.clear()
        }

        if (noMoreItems) {
            return // nothing to fetch until a new search is performed
        }

        nextPageStartIndex?.let { pageStartIndex ->

            // make the call only in case of valid NextPage

            val disposable = PhotosManager.INSTANCE.searchPhotos(RESULT_COUNT, pageStartIndex, searchTerm)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    handleResponse(it)
                },{
                    // happens when no more results are available apparently :[
                    noMoreItems = true
                    handleResponse(null)
                })

            compositeDisposable.add(disposable)
        }

    }

    private fun handleResponse(photosResponse: PhotosSearchResponse?) {
        isLoading.value = false

        nextPageStartIndex = photosResponse?.queries?.nextPage?.firstOrNull()?.startIndex
        if (nextPageStartIndex == null) {
            noMoreItems = true
        }

        if (photosResponse != null && photosResponse?.queries?.previousPage == null) {
            // new query
            photosAdapter.clear()
        }

        // add the items
        photosAdapter.addMoreItems(photosResponse?.items ?: listOf(), !noMoreItems)
    }

    fun getPhotosAdapter(): PhotosAdapter {
        return photosAdapter
    }

    override fun onLoaderAppeared() {
        submitSearch(lastSearchTerm, false)
    }

    override fun onImageSelected(photoItem: PhotoItem) {
        photoItemSelected.value = photoItem
        // reset
        photoItemSelected.value = null
    }

}
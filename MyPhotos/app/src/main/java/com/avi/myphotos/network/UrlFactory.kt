package com.avi.myphotos.network

object UrlFactory {

    const val PHOTOS_BASE_URL = "https://www.googleapis.com/"

    fun getSearchUrl(pageSize: Int, currentPage: Int, query: String): String {
        return "customsearch/v1?key=AIzaSyCzREl07Bc_bk2hm65RtiaJ0hJ48R_nMfg&cx=005800383728131713214:5jocmduwqum&searchType=image&num=$pageSize&start=$currentPage&q=$query"
    }

}

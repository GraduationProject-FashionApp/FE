package com.gradu.lookthat.views.search.api

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import java.io.File

interface APIinterface {
    @Multipart
    @POST("/find/")
    fun getSearchResult(
        @Part multipartFile: MultipartBody.Part
    ) : Call<SearchResponse>
}
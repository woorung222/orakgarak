package com.capstone.orakgarak

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverApiService {

    @GET("/v1/search/local.json")
    fun searchPlaces(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Query("query") query: String
    ): Call<NaverApiResponse>
}

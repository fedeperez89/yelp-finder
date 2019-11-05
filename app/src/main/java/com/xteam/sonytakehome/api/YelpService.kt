package com.xteam.sonytakehome.api

import retrofit2.http.GET
import retrofit2.http.Query

interface YelpService {

    @GET("business/search")
    suspend fun search(@Query("term") term: String, @Query("latitude") lat: Float, @Query("longitude") lon: Float): SearchResponse
}
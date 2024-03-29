package com.xteam.sonytakehome.api

import com.xteam.sonytakehome.model.BusinessDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Yelp API.
 */
interface YelpService {

    @GET("businesses/search")
    suspend fun search(
        @Query("term") term: String, @Query("latitude") lat: Float,
        @Query("longitude") lon: Float, @Query("limit") limit: Int
    ): SearchResponse

    @GET("businesses/{id}")
    suspend fun businessDetails(@Path("id") id: String): BusinessDetail
}
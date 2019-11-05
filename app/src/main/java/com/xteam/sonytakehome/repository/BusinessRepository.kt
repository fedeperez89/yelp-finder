package com.xteam.sonytakehome.repository

import com.xteam.sonytakehome.api.YelpService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BusinessRepository @Inject constructor(
    private val yelpService: YelpService
) {


    suspend fun businessDetails(id: String) = yelpService.businessDetails(id)

    suspend fun searchBusiness(query: String) = yelpService.search(query, 42.9f, -122f)

}
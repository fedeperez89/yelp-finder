package com.xteam.sonytakehome.repository

import com.xteam.sonytakehome.api.YelpService
import com.xteam.sonytakehome.db.BusinessDao
import com.xteam.sonytakehome.db.YelpDB
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BusinessRepository @Inject constructor(
    private val db: YelpDB,
    private val businessDao: BusinessDao,
    private val yelpService: YelpService
) {

    suspend fun searchBusiness(query: String) = yelpService.search(query, 42.9f, -122f)

}
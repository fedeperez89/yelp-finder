package com.xteam.sonytakehome.repository

import android.util.Log
import com.xteam.sonytakehome.api.SearchResponse
import com.xteam.sonytakehome.api.YelpService
import com.xteam.sonytakehome.model.BusinessDetail
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class BusinessRepository @Inject constructor(
    private val yelpService: YelpService
) {


    suspend fun businessDetails(id: String): Resource<BusinessDetail> {
        return try {
            val response = yelpService.businessDetails(id)
            Resource.success(response)
        } catch (e: Exception) {
            Log.e("BusinessRepository", "Error making details request")
            Resource.error(e.message.orEmpty(), null)
        }

    }

    suspend fun searchBusiness(query: String): Resource<SearchResponse> {
        return try {
            val response = yelpService.search(query, 42.9f, -122f, 50)
            Resource.success(response)
        } catch (e: Exception) {
            Log.e("BusinessRepository", "Error making search request")
            Resource.error(e.message.orEmpty(), null)
        }
    }

}


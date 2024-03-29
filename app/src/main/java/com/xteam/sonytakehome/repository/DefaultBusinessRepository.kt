package com.xteam.sonytakehome.repository

import android.util.Log
import com.xteam.sonytakehome.api.SearchResponse
import com.xteam.sonytakehome.api.YelpService
import com.xteam.sonytakehome.model.BusinessDetail
import javax.inject.Inject
import javax.inject.Singleton

/**
 * [BusinessRepository] implementation that makes requests to the API.
 */
@Singleton
class DefaultBusinessRepository @Inject constructor(
    private val yelpService: YelpService
) : BusinessRepository {

    override suspend fun businessDetails(id: String): Resource<BusinessDetail> {
        return try {
            val response = yelpService.businessDetails(id)
            Resource.success(response)
        } catch (e: Exception) {
            Log.e("BusinessRepository", "Error making details request")
            Resource.error(e.message.orEmpty(), null)
        }

    }

    override suspend fun searchBusiness(query: String): Resource<SearchResponse> {
        return try {
            val response = yelpService.search(query, 40.7831f, -73.9712f, 50)
            Resource.success(response)
        } catch (e: Exception) {
            Log.e("BusinessRepository", "Error making search request")
            Resource.error(e.message.orEmpty(), null)
        }
    }

}


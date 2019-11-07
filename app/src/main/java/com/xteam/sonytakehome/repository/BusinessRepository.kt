package com.xteam.sonytakehome.repository

import com.xteam.sonytakehome.api.SearchResponse
import com.xteam.sonytakehome.model.BusinessDetail

/**
 * Repository for Yelp Businesses.
 */
interface BusinessRepository {

    /**
     * Get detailed info using a business id.
     * @param id business id.
     */
    suspend fun businessDetails(id: String): Resource<BusinessDetail>

    /**
     * Search for businesses.
     * @param query term to search for.
     */
    suspend fun searchBusiness(query: String): Resource<SearchResponse>
}
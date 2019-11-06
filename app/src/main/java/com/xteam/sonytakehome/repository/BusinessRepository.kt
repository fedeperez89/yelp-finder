package com.xteam.sonytakehome.repository

import com.xteam.sonytakehome.api.SearchResponse
import com.xteam.sonytakehome.model.BusinessDetail

interface BusinessRepository {

    suspend fun businessDetails(id: String): Resource<BusinessDetail>

    suspend fun searchBusiness(query: String): Resource<SearchResponse>
}
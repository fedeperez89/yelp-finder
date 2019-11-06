package com.xteam.sonytakehome.repository

import com.xteam.sonytakehome.api.SearchResponse
import com.xteam.sonytakehome.model.Business
import com.xteam.sonytakehome.model.BusinessDetail

/**
 *  A fake repository to use in tests.
 *  @param businessSearch list that will be returned when search is called.
 *  @param detail will be returned when businessDetails is called.
 */
class FakeBusinessRepository(
    private val businessSearch: List<Business>,
    private val detail: BusinessDetail
) : BusinessRepository {

    override suspend fun businessDetails(id: String): Resource<BusinessDetail> {
        return Resource.success(detail)
    }

    override suspend fun searchBusiness(query: String): Resource<SearchResponse> = Resource.success(
        SearchResponse(businessSearch.size, businessSearch)
    )

}
package com.xteam.sonytakehome.api

import com.squareup.moshi.JsonClass
import com.xteam.sonytakehome.model.Business

@JsonClass(generateAdapter = true)
data class SearchResponse(val total: Int, val businesses: List<Business>)
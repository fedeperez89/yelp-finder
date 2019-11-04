package com.xteam.sonytakehome.api

import com.xteam.sonytakehome.model.Business

data class SearchResponse(val total: Int, val businesses: List<Business>)
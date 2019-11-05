package com.xteam.sonytakehome.ui.detail

data class BusinessDetailPresentationObject(
    val id: String,
    val name: String,
    val rating: Float,
    val price: String,
    val reviews: Int,
    val photos: List<String>,
    val category: String,
    val address: String,
    val phoneNumber: String
)
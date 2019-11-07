package com.xteam.sonytakehome.ui.detail

import com.xteam.sonytakehome.model.Open

/**
 * Value object with information ready to be presented at Detail Screen.
 */
data class BusinessDetailPresentationObject(
    val id: String,
    val name: String,
    val rating: Float,
    val price: String,
    val reviews: Int,
    val photos: List<String>,
    val category: String,
    val address: String,
    val phoneNumber: String,
    val hours: List<Open>
)
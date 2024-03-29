package com.xteam.sonytakehome.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Business(
    val id: String,
    val display_phone: String,
    val distance: Float,
    val alias: String,
    val image_url: String,
    val is_closed: Boolean,
    val name: String,
    val phone: String,
    val price: String?,
    val rating: Float,
    val review_count: Int,
    val url: String
)
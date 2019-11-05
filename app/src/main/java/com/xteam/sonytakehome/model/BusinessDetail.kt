package com.xteam.sonytakehome.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    val address1: String?,
    val address2: String?,
    val address3: String?,
    val city: String?,
    val zip_code: String?,
    val country: String?,
    val state: String?,
    val display_address: List<String>,
    val cross_streets: String?
)


@JsonClass(generateAdapter = true)
data class SpecialHours(
    val date: String?,
    val is_closed: Boolean,
    val start: String?,
    val end: String?,
    val is_overnight: Boolean
)

@JsonClass(generateAdapter = true)
data class Open(
    val is_overnight: Boolean,
    val start: Int,
    val end: Int,
    val day: Int
)

@JsonClass(generateAdapter = true)
data class Categories(
    val alias: String,
    val title: String
)

@JsonClass(generateAdapter = true)
data class Coordinates(
    val latitude: Double,
    val longitude: Double
)

@JsonClass(generateAdapter = true)
data class Hours(
    val open: List<Open>,
    val hours_type: String,
    val is_open_now: Boolean
)

@JsonClass(generateAdapter = true)
data class BusinessDetail(
    val id: String,
    val alias: String,
    val name: String,
    val image_url: String,
    val is_claimed: Boolean,
    val is_closed: Boolean,
    val url: String,
    val phone: String,
    val display_phone: String,
    val review_count: Int,
    val categories: List<Categories>?,
    val rating: Float,
    val location: Location,
    val coordinates: Coordinates,
    val photos: List<String>?,
    val price: String?,
    val hours: List<Hours>?,
    val transactions: List<String>?,
    val special_hours: List<SpecialHours>?

)
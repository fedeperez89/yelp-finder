package com.xteam.sonytakehome.api

import com.xteam.sonytakehome.model.*

class TestYelpService : YelpService {

    override suspend fun search(term: String, lat: Float, lon: Float, limit: Int): SearchResponse {
        when (term) {
            "Pizza" -> return SearchResponse(
                1, listOf(
                    Business(
                        "ivThaN2DyC0YpR7pxbh1CEwd",
                        "(541) 878-1474",
                        1.0f,
                        "pizza-at-the-cove-shady-cove",
                        "https://s3-media2.fl.yelpcdn.com/bphoto/zeJU4oOEWYvObewmhZNyzw/o.jpg",
                        false,
                        "Pizza at the Cove",
                        "+15418781474",
                        "$$",
                        3.0f,
                        38,
                        "https://www.yelp.com/biz/pizza-at-the-cove-shady-cove?adjust_creative=_FfUHflj1Q3jJFZkkWH0Ig&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=_FfUHflj1Q3jJFZkkWH0Ig"
                    )
                )
            )
            "Error" -> throw Exception("Something happened")
            else -> return SearchResponse(0, listOf())
        }
    }

    override suspend fun businessDetails(id: String): BusinessDetail = BusinessDetail(
        "ivThaN2DyC0YpR7pxbh1CEwd",
        "pizza-at-the-cove-shady-cove",
        "Pizza at the Cove",
        "https://s3-media2.fl.yelpcdn.com/bphoto/zeJU4oOEWYvObewmhZNyzw/o.jpg",
        is_claimed = true,
        is_closed = false,
        url = "https://www.yelp.com/biz/pizza-at-the-cove-shady-cove?adjust_creative=_FfUHflj1Q3jJFZkkWH0Ig&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=_FfUHflj1Q3jJFZkkWH0Ig",
        phone = "+15418781474",
        display_phone = "(541) 878-1474",
        review_count = 38,
        categories = listOf(Category("pizza", "Pizza")),
        rating = 3.0f,
        location = Location(
            "22251 Hwy 62", null, null, "Shady Cove", "97539", "US", "OR", listOf(
                "22251 Hwy 62",
                "Shady Cove, OR 97539"
            ), ""
        ),
        coordinates = Coordinates(42.6205825805664, -122.809715270996),
        photos = listOf(
            "https://s3-media2.fl.yelpcdn.com/bphoto/zeJU4oOEWYvObewmhZNyzw/o.jpg",
            "https://s3-media3.fl.yelpcdn.com/bphoto/P7wYiCbCAqmEmsjtwtOVlA/o.jpg",
            "https://s3-media2.fl.yelpcdn.com/bphoto/CSifsLH5rwd65ovzMbRNRg/o.jpg"
        ),
        price = "$$",
        hours = listOf(
            Hours(
                listOf(
                    Open(false, "1600", "2100", 0),
                    Open(false, "1600", "2100", 1),
                    Open(false, "1600", "2100", 2),
                    Open(false, "1600", "2100", 3),
                    Open(false, "1600", "2100", 4),
                    Open(false, "1600", "2100", 5),
                    Open(false, "1600", "2100", 6)
                ), "REGULAR", false
            )
        ),
        transactions = listOf(),
        special_hours = listOf()
    )

}
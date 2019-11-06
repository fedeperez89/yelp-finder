package com.xteam.sonytakehome

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.xteam.sonytakehome.model.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }

    this.observeForever(observer)

    // Don't wait indefinitely if the LiveData is not set.
    if (!latch.await(time, timeUnit)) {
        throw TimeoutException("LiveData value was never set.")
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}

val business = Business(
    "id",
    "123456",
    0.1f,
    "alias",
    "image_url",
    false,
    "Tony Pizza",
    "123456",
    "$$",
    4.5f,
    38,
    "http://tony.com"
)

val businessDetail = BusinessDetail(
    "id",
    "alias",
    "Tony Pizza",
    "image_url",
    true,
    false,
    "http://tony.com",
    "123456",
    "123456",
    38,
    listOf(Category("pizza", "Pizza place")),
    4.5f,
    Location("123 fake street", null, null, "Springfield", "1234", "USA", "AZ", listOf(), null),
    Coordinates(-44.0, 122.0),
    listOf("image_url"),
    "$$",
    listOf(),
    listOf(),
    listOf()
)
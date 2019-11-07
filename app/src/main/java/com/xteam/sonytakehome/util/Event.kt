package com.xteam.sonytakehome.util

import androidx.lifecycle.Observer

/**
 * Wrapper for events exposed via LiveData.
 */
open class Event<out T>(private val content: T) {

    private var hasBeenHandled = false

    /**
     * Get the content and prevent further use.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Return the content, even if it has been handled. Doing so will not mark the event as handled.
     */
    fun peekContent(): T = content
}

/**
 * Observer to simplify observing unhandled events.
 */
class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}
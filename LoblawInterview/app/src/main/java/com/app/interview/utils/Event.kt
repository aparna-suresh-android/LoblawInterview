package com.app.interview.utils

class Event<out T>(private var item: T?) {
    private var isItemConsumed = false

    fun getItemIfNotConsumed(): T? {
        if (isItemConsumed)
            return null
        else {
            isItemConsumed = true
            return item
        }

    }
}
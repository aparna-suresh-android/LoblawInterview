package com.app.interview.utils

interface OnListItemUpdated<in T> {
    fun updateItems(updatedItems: List<T>)
}
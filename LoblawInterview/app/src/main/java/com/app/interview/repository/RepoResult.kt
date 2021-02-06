package com.app.interview.repository

class RepoResult<out T>(val status: Status, val obj: T? = null, val error: String? = null) {
    enum class Status {
        SUCCESS,
        ERROR
    }
}
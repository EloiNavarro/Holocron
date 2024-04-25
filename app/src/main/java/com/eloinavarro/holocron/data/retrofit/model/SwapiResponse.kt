package com.eloinavarro.holocron.data.retrofit.model

data class SwapiResponse<T>(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<T>
)
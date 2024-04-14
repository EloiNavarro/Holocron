package com.eloinavarro.holocron.domain

data class SWPlanet(
    val id: Int,
    val name: String,
    val image: String,
    val diameter: Int,
    val climate: String,
    val water: Int,
    val terrain: String,
    val gravity: String,
    val hoursForDay: Int, //rotation_period
    val daysForYear: Int, //orbital_period
    val population: Int,
    val links:List<SwLink>,
    val url: String,
    val isFavorite: Boolean
)
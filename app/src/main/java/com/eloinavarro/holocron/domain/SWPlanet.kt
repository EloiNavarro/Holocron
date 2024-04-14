package com.eloinavarro.holocron.domain

data class SWPlanet(
    override val id: Int,
    override val name: String,
    val image: String,
    val diameter: Int,
    val climate: String,
    val water: Int,
    val terrain: String,
    val gravity: String,
    val hoursForDay: Int, //rotation_period
    val daysForYear: Int, //orbital_period
    val population: Int,
    override val links:List<SwLink>,
    override val url: String,
    override val isFavorite: Boolean
):SWItem
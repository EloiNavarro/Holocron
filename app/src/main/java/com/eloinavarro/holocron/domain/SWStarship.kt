package com.eloinavarro.holocron.domain

data class SWStarship(
    val id: Int,
    val name: String,
    val model: String,
    val starshipClass: String,
    val manufacturer: String,
    val value: Int,
    val length: Int,
    val crew: Int,
    val passengers: Int,
    val speedInAtmosphere: Int?,
    val hyperdrive: Float,
    val megalights: String,
    val cargoSize: Int,
    val consumables: String,
    val links: List<SwLink>,
    val url: String
)

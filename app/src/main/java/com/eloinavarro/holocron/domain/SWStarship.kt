package com.eloinavarro.holocron.domain

data class SWStarship(
    override val id: Int,
    override val name: String,
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
    override val links: List<SwLink>,
    override val url: String,
    override val isFavorite: Boolean
):SWItem

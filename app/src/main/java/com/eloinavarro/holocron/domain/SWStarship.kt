package com.eloinavarro.holocron.domain

data class SWStarship(
    override val id: Int,
    override val name: String,
    override val image: String,
    val model: String,
    val starshipClass: String,
    val manufacturer: String,
    val value: Int,
    val length: Float,
    val crew: Int,
    val passengers: Int,
    val speedInAtmosphere: Int?,
    val hyperdrive: Float,
    val megalights: String,
    val cargoSize: Int,
    val consumables: String,
    override var links: List<SwLinkList>,
    override val url: String,
    override val isFavorite: Boolean
):SWItem

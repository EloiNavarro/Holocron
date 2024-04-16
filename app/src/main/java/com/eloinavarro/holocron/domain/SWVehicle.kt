package com.eloinavarro.holocron.domain

data class SWVehicle(
    override val id: Int,
    override val name: String,
    override val image: String,
    val model: String,
    val manufacturer: String,
    val value: Int,
    val length: Float,
    val speedInAtmosphere: Int?,
    val crew: Int,
    val passengers: Int,
    val consumables: String,
    val vehicleClass: String,
    override var links: List<SwLinkList>,
    override val url: String,
    override val isFavorite: Boolean
):SWItem

package com.eloinavarro.holocron.domain

data class SWSpecie(
    override val id: Int,
    override val name: String,
    override val image: String,
    val classification: String,
    val designation: String,
    val averageHeight: String,
    val averageLifespan: String,
    val skinColors: List<String>,
    val eyeColors: List<String>,
    val hairColors: List<String>,
    override var links:List<SwLinkList>,
    override val url: String,
    override var isFavorite: Boolean
):SWItem
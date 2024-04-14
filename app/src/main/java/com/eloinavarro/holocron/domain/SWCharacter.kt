package com.eloinavarro.holocron.domain

data class SWCharacter(
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val bornDate: String,
    val appearance: SwCharacterAppearance,
    val links:List<SwLink>,
    val url: String,
    var isFavorite: Boolean
)
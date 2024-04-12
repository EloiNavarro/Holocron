package com.eloinavarro.holocron.domain

data class SWCharacter(
    val id: String,
    val name: String,
    val description: String,
    val image: String,
    val bornDate: String,
    val appearance: SwCharacterAppearance,
    val links:List<SwLink>,
    val url: String
)

enum class SwLinkType {
    MOVIE, SPECIE, VEHICLE, STARSHIP
}
data class SwLink(val url: String, val type: SwLinkType)
data class SwCharacterAppearance(val height: Float?, val weight: Int?, val colors: SWCharacterColors)
data class SWCharacterColors(val eyes: String, val hair: String, val skin: String)
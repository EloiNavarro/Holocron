package com.eloinavarro.holocron.domain

enum class SwLinkType {
    MOVIE, SPECIE, VEHICLE, STARSHIP, CHARACTER
}
data class SwLink(val url: String, val type: SwLinkType)
data class SwCharacterAppearance(val height: Float?, val weight: Int?, val colors: SWCharacterColors)
data class SWCharacterColors(val eyes: String, val hair: String, val skin: String)

interface SWItem {
    val id: Int
    val name: String
    val links: List<SwLink>
    val url: String
    val isFavorite: Boolean
}
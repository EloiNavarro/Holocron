package com.eloinavarro.holocron.domain

enum class SwLinkType {
    MOVIE, SPECIE, VEHICLE, STARSHIP, CHARACTER, PLANET
}
data class SwLinkList(val type: SwLinkType, val links: List<SwLink>)
data class SwLink(val id: Int, var name: String = "")

interface SWItem {
    val id: Int
    val name: String
    val image: String
    val links: List<SwLinkList>
    val url: String
    val isFavorite: Boolean
}
package com.eloinavarro.holocron.domain

enum class SwLinkType {
    MOVIE, SPECIE, VEHICLE, STARSHIP, CHARACTER, PLANET
}
data class SwLinkList(val type: SwLinkType, val links: List<SwLink>)
data class SwLink(val id: Int, var name: String = "")
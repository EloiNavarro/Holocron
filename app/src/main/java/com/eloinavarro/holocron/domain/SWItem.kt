package com.eloinavarro.holocron.domain

interface SWItem {
    val id: Int
    val name: String
    val image: String
    val links: List<SwLinkList>
    val url: String
    val isFavorite: Boolean
}
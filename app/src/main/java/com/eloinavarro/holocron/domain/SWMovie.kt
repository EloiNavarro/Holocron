package com.eloinavarro.holocron.domain

data class SWMovie(
    override val id: Int,
    override val name: String,
    val opening: String,
    val director: String,
    val producer: String,
    val releaseDate: String,
    val episode: Int,
    override val image: String,
    override var links: List<SwLinkList>,
    override val url: String,
    override val isFavorite: Boolean
):SWItem

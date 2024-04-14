package com.eloinavarro.holocron.domain

data class SWCharacter(
    override val id: Int,
    override val name: String,
    val description: String,
    val image: String,
    val bornDate: String,
    val appearance: SwCharacterAppearance,
    override val links:List<SwLink>,
    override val url: String,
    override var isFavorite: Boolean
):SWItem
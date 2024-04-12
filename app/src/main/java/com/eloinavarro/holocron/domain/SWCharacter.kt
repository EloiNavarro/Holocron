package com.eloinavarro.holocron.domain

data class SWCharacter(
    val id: String,
    val name: String,
    val description: String,
    val image: String,
    val bornDate: String,
    val colors: SWCharacterColors,
    val height: String,
    val mass: String
)

data class SWCharacterColors(val eyes: String, val hair: String, val skin: String)
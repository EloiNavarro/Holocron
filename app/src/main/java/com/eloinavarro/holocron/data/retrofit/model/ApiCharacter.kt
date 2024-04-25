package com.eloinavarro.holocron.data.retrofit.model

data class ApiCharacter(
    val id: Int,
    val affiliations: List<String>,
    val apprentices: List<String>,
    val born: Int,
    val bornLocation: String,
    val cybernetics: String,
    val died: Int,
    val diedLocation: String,
    val eyeColor: String,
    val formerAffiliations: List<String>,
    val gender: String,
    val hairColor: String,
    val height: Double,
    val homeworld: String,
    val image: String,
    val mass: Int,
    val masters: List<String>,
    val name: String,
    val skinColor: String,
    val species: String,
    val wiki: String
)
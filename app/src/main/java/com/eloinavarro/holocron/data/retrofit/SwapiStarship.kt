package com.eloinavarro.holocron.data.retrofit

import com.eloinavarro.holocron.domain.SWStarship
import com.eloinavarro.holocron.domain.SwLink
import com.eloinavarro.holocron.domain.SwLinkType

data class SwapiStarship(
    val name: String,
    val model: String,
    val starship_class: String,
    val manufacturer: String,
    val cost_in_credits: String,
    val length: String,
    val crew: String,
    val passengers: String,
    val max_atmosphering_speed: String,
    val hyperdrive_rating: String,
    val MGLT: String,
    val cargo_capacity: String,
    val consumables: String,
    val films: List<String>,
    val pilots: List<String>,
    val created: String,
    val edited: String,
    val url: String
)

fun SwapiStarship.toDomainModel(): SWStarship {
    val id = url.substringBeforeLast("/", "").substringAfterLast("/").toInt()
    return SWStarship(
        id = id,
        name = name,
        model = model,
        starshipClass = starship_class,
        manufacturer = manufacturer,
        value = cost_in_credits.toInt(),
        length = length.toInt(),
        crew = crew.toInt(),
        passengers = passengers.toInt(),
        speedInAtmosphere = max_atmosphering_speed.toIntOrNull(),
        hyperdrive = hyperdrive_rating.toFloat(),
        megalights = MGLT,
        cargoSize = cargo_capacity.toInt(),
        consumables = consumables,
        links = films.map { SwLink(it, SwLinkType.MOVIE) } +
                pilots.map { SwLink(it, SwLinkType.CHARACTER) },
        url = url
    )
}
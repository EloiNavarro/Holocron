package com.eloinavarro.holocron.data.retrofit

import com.eloinavarro.holocron.domain.SWPlanet
import com.eloinavarro.holocron.domain.SwLink
import com.eloinavarro.holocron.domain.SwLinkType

data class SwapiPlanet(
    val name: String,
    val rotation_period: String,
    val orbital_period: String,
    val diameter: String,
    val climate: String,
    val gravity: String,
    val terrain: String,
    val surface_water: String,
    val population: String,
    val residents: List<String>,
    val films: List<String>,
    val created: String,
    val edited: String,
    val url: String
)

fun SwapiPlanet.toDomainModel(): SWPlanet {
    val id = url.substringBeforeLast("/", "").substringAfterLast("/").toInt()
    val color = nameToHexColor(name)
    return SWPlanet(
        id = id,
        name = name,
        image = "https://singlecolorimage.com/get/$color/300x300",
        diameter = diameter.toInt(),
        climate = climate,
        water = surface_water.toIntOrNull() ?: -1,
        terrain = terrain,
        gravity = gravity,
        hoursForDay = rotation_period.toInt(),
        daysForYear = orbital_period.toInt(),
        population = population.toInt(),
        links = films.map { SwLink(it, SwLinkType.MOVIE) } +
                residents.map { SwLink(it, SwLinkType.CHARACTER) },
        url = url,
        isFavorite = false
    )
}

private fun nameToHexColor(name: String): String {
    val hashCode = name.hashCode()
    val hexString = Integer.toHexString(hashCode)
    return hexString.padStart(6, '0')
}
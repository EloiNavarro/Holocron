package com.eloinavarro.holocron.data

import com.eloinavarro.holocron.data.retrofit.SwapiCharacter
import com.eloinavarro.holocron.data.retrofit.SwapiMovie
import com.eloinavarro.holocron.data.retrofit.SwapiPlanet
import com.eloinavarro.holocron.data.retrofit.SwapiSpecie
import com.eloinavarro.holocron.data.retrofit.SwapiStarship
import com.eloinavarro.holocron.data.retrofit.SwapiVehicle
import com.eloinavarro.holocron.domain.SWCharacter
import com.eloinavarro.holocron.domain.SWMovie
import com.eloinavarro.holocron.domain.SWPlanet
import com.eloinavarro.holocron.domain.SWSpecie
import com.eloinavarro.holocron.domain.SWStarship
import com.eloinavarro.holocron.domain.SWVehicle
import com.eloinavarro.holocron.domain.SwLink
import com.eloinavarro.holocron.domain.SwLinkList
import com.eloinavarro.holocron.domain.SwLinkType
import java.net.URLEncoder

fun SwapiCharacter.toDomainModel(): SWCharacter {
    val encodedName = URLEncoder.encode(name, "UTF-8")
    return SWCharacter(
        id = url.toId(),
        name = name,
        description = "",
        image = "https://via.placeholder.com/500x500/${randomColor(url.toId())}/FFFFFF?text=$encodedName",
        bornDate = birth_year,
        height = height.toFloatOrNull()?.div(100),
        weight = mass.toIntOrNull(),
        skinColors = skin_color.split(", "),
        eyeColors = eye_color.split(", "),
        hairColors = hair_color.split(", "),
        links = listOf(
            SwLinkList(SwLinkType.MOVIE, films.map { SwLink(it.toId()) }),
            SwLinkList(SwLinkType.SPECIE, species.map { SwLink(it.toId()) }),
            SwLinkList(SwLinkType.VEHICLE, vehicles.map { SwLink(it.toId()) }),
            SwLinkList(SwLinkType.STARSHIP, starships.map { SwLink(it.toId()) })
        ),
        url = url,
        isFavorite = false
    )
}

fun SwapiPlanet.toDomainModel(): SWPlanet {
    val color = nameToHexColor(name)
    return SWPlanet(
        id = url.toId(),
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
        links = listOf(
            SwLinkList(SwLinkType.MOVIE, films.map { SwLink(it.toId()) }),
            SwLinkList(SwLinkType.CHARACTER, residents.map { SwLink(it.toId()) })
        ),
        url = url,
        isFavorite = false
    )
}

fun SwapiStarship.toDomainModel(): SWStarship {
    val encodedName = URLEncoder.encode(model, "UTF-8")
    return SWStarship(
        id = url.toId(),
        name = name,
        image = "https://picsum.photos/seed/$encodedName/500/500",
        model = model,
        starshipClass = starship_class,
        manufacturer = manufacturer,
        value = cost_in_credits.toIntOrNull() ?: -1,
        length = length.toFloat(),
        crew = crew.toInt(),
        passengers = passengers.toInt(),
        speedInAtmosphere = max_atmosphering_speed.toIntOrNull(),
        hyperdrive = hyperdrive_rating.toFloat(),
        megalights = MGLT,
        cargoSize = cargo_capacity.toInt(),
        consumables = consumables,
        links = listOf(
            SwLinkList(SwLinkType.MOVIE, films.map { SwLink(it.toId()) }),
            SwLinkList(SwLinkType.CHARACTER, pilots.map { SwLink(it.toId()) })
        ),
        url = url,
        isFavorite = false
    )
}

fun SwapiSpecie.toDomainModel(): SWSpecie {
    var homeWorldPlanet = emptyList<SwLinkList>()
    homeworld?.let {
        homeWorldPlanet = listOf(SwLinkList(SwLinkType.PLANET, listOf(SwLink(homeworld.toId()))))
    }
    return SWSpecie(
        id = url.toId(),
        name = name,
        image = "",
        classification = classification,
        designation = designation,
        averageHeight = average_height,
        averageLifespan = average_lifespan,
        skinColors = skin_colors.split(", "),
        eyeColors = eye_colors.split(", "),
        hairColors = hair_colors.split(", "),
        links = homeWorldPlanet + listOf(
            SwLinkList(SwLinkType.CHARACTER, people.map { SwLink(it.toId()) }),
            SwLinkList(SwLinkType.MOVIE, films.map { SwLink(it.toId()) })
        ),
        url = url,
        isFavorite = false
    )
}

fun SwapiVehicle.toDomainModel(): SWVehicle {
    return SWVehicle(
        id = url.toId(),
        name = name,
        image = "",
        model = model,
        vehicleClass = vehicle_class,
        manufacturer = manufacturer,
        value = cost_in_credits.toIntOrNull() ?: -1,
        length = length.toFloat(),
        crew = crew.toInt(),
        passengers = passengers.toInt(),
        speedInAtmosphere = max_atmosphering_speed.toIntOrNull(),
        consumables = consumables,
        links = listOf(
            SwLinkList(SwLinkType.MOVIE, films.map { SwLink(it.toId()) }),
            SwLinkList(SwLinkType.CHARACTER, pilots.map { SwLink(it.toId()) })
        ),
        url = url,
        isFavorite = false
    )
}

fun SwapiMovie.toDomainModel(): SWMovie {
    return SWMovie (
        id = url.toId(),
        name = title,
        image = "",
        director = director,
        producer = producer,
        opening = opening_crawl,
        episode = episode_id,
        releaseDate = release_date,
        links = listOf(
            SwLinkList(SwLinkType.CHARACTER, characters.map { SwLink(it.toId()) }),
            SwLinkList(SwLinkType.PLANET, planets.map { SwLink(it.toId()) }),
            SwLinkList(SwLinkType.STARSHIP, starships.map { SwLink(it.toId()) }),
            SwLinkList(SwLinkType.VEHICLE, vehicles.map { SwLink(it.toId()) }),
            SwLinkList(SwLinkType.SPECIE, species.map { SwLink(it.toId()) })
        ),
        url = url,
        isFavorite = false
    )
}

private fun nameToHexColor(name: String): String {
    val hashCode = name.hashCode()
    val hexString = Integer.toHexString(hashCode)
    return hexString.padStart(6, '0')
}

private fun randomColor(id: Int): String {
    return when (id % 5) {
        0 -> "309AC7" //Blue
        1 -> "21A667" //Green
        2 -> "F5DF39" //Yellow
        3 -> "E16D40" //Orange
        else -> "B66FB6" //Purple
    }
}

fun String.toId():Int = substringBeforeLast("/", "").substringAfterLast("/").toInt()

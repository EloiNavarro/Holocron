package com.eloinavarro.holocron.data.room.model

import androidx.room.Entity
import com.eloinavarro.holocron.domain.SWPlanet
import com.eloinavarro.holocron.domain.SwLinkList

@Entity(tableName = "SWPlanet", primaryKeys = ["id"])
data class RoomPlanet(
    val id: Int,
    val name: String,
    val image: String,
    val diameter: Int,
    val climate: String,
    val water: Int,
    val terrain: String,
    val gravity: String,
    val hoursForDay: Int, //rotation_period
    val daysForYear: Int, //orbital_period
    val population: Int,
    var links: List<SwLinkList>,
    val url: String,
    val isFavorite: Boolean
) {
    companion object {
        fun fromDomain(it: SWPlanet): RoomPlanet {
            return RoomPlanet(
                id = it.id,
                name = it.name,
                image = it.image,
                diameter = it.diameter,
                climate = it.climate,
                water = it.water,
                terrain = it.terrain,
                gravity = it.gravity,
                hoursForDay = it.hoursForDay,
                daysForYear = it.daysForYear,
                population = it.population,
                links = it.links,
                url = it.url,
                isFavorite = it.isFavorite
            )
        }
    }
}

fun RoomPlanet.toDomainModel(): SWPlanet {
    return SWPlanet(
        id = id,
        name = name,
        image = image,
        diameter = diameter,
        climate = climate,
        water = water,
        terrain = terrain,
        gravity = gravity,
        hoursForDay = hoursForDay,
        daysForYear = daysForYear,
        population = population,
        links = links,
        url = url,
        isFavorite = isFavorite
    )
}
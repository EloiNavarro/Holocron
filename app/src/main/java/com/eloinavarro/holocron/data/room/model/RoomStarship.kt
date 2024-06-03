package com.eloinavarro.holocron.data.room.model

import androidx.room.Entity
import com.eloinavarro.holocron.domain.SWStarship
import com.eloinavarro.holocron.domain.SwLinkList

@Entity(tableName = "SWStarship", primaryKeys = ["id"])
data class RoomStarship(
    val id: Int,
    val name: String,
    val image: String,
    val model: String,
    val starshipClass: String,
    val manufacturer: String,
    val value: Int,
    val length: Float,
    val crew: Int,
    val passengers: Int,
    val speedInAtmosphere: Int?,
    val hyperdrive: Float,
    val megalights: String,
    val cargoSize: Int,
    val consumables: String,
    var links: List<SwLinkList>,
    val url: String,
    val isFavorite: Boolean
) {
    companion object {
        fun fromDomain(it: SWStarship): RoomStarship {
            return RoomStarship(
                id = it.id,
                name = it.name,
                image = it.image,
                model = it.model,
                starshipClass = it.starshipClass,
                manufacturer = it.manufacturer,
                value = it.value,
                length = it.length,
                crew = it.crew,
                passengers = it.passengers,
                speedInAtmosphere = it.speedInAtmosphere,
                hyperdrive = it.hyperdrive,
                megalights = it.megalights,
                cargoSize = it.cargoSize,
                consumables = it.consumables,
                links = it.links,
                url = it.url,
                isFavorite = it.isFavorite
            )
        }
    }
}

fun RoomStarship.toDomainModel(): SWStarship {
    return SWStarship(
        id = id,
        name = name,
        image = image,
        model = model,
        starshipClass = starshipClass,
        manufacturer = manufacturer,
        value = value,
        length = length,
        crew = crew,
        passengers = passengers,
        speedInAtmosphere = speedInAtmosphere,
        hyperdrive = hyperdrive,
        megalights = megalights,
        cargoSize = cargoSize,
        consumables = consumables,
        links = links,
        url = url,
        isFavorite = isFavorite
    )
}
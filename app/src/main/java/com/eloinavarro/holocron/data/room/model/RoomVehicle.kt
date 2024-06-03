package com.eloinavarro.holocron.data.room.model

import androidx.room.Entity
import com.eloinavarro.holocron.domain.SWVehicle
import com.eloinavarro.holocron.domain.SwLinkList

@Entity(tableName = "SWVehicle", primaryKeys = ["id"])
data class RoomVehicle(
    val id: Int,
    val name: String,
    val image: String,
    val model: String,
    val manufacturer: String,
    val value: Int,
    val length: Float,
    val speedInAtmosphere: Int?,
    val crew: Int,
    val passengers: Int,
    val consumables: String,
    val vehicleClass: String,
    var links: List<SwLinkList>,
    val url: String,
    val isFavorite: Boolean
) {
    companion object {
        fun fromDomain(it: SWVehicle): RoomVehicle {
            return RoomVehicle(
                id = it.id,
                name = it.name,
                image = it.image,
                model = it.model,
                manufacturer = it.manufacturer,
                value = it.value,
                length = it.length,
                speedInAtmosphere = it.speedInAtmosphere,
                crew = it.crew,
                passengers = it.passengers,
                consumables = it.consumables,
                vehicleClass = it.vehicleClass,
                links = it.links,
                url = it.url,
                isFavorite = it.isFavorite
            )
        }
    }
}

fun RoomVehicle.toDomainModel(): SWVehicle {
    return SWVehicle(
        id = id,
        name = name,
        image = image,
        model = model,
        manufacturer = manufacturer,
        value = value,
        length = length,
        speedInAtmosphere = speedInAtmosphere,
        crew = crew,
        passengers = passengers,
        consumables = consumables,
        vehicleClass = vehicleClass,
        links = links,
        url = url,
        isFavorite = isFavorite
    )
}
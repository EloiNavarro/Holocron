package com.eloinavarro.holocron.data.room.model

import androidx.room.Entity
import com.eloinavarro.holocron.domain.SWSpecie
import com.eloinavarro.holocron.domain.SwLinkList

@Entity(tableName = "SWSpecie", primaryKeys = ["id"])
data class RoomSpecie(
    val id: Int,
    val name: String,
    val image: String,
    val classification: String,
    val designation: String,
    val averageHeight: String,
    val averageLifespan: String,
    val skinColors: List<String>,
    val eyeColors: List<String>,
    val hairColors: List<String>,
    var links: List<SwLinkList>,
    val url: String,
    var isFavorite: Boolean
) {
    companion object {
        fun fromDomain(it: SWSpecie): RoomSpecie {
            return RoomSpecie(
                id = it.id,
                name = it.name,
                image = it.image,
                classification = it.classification,
                designation = it.designation,
                averageHeight = it.averageHeight,
                averageLifespan = it.averageLifespan,
                skinColors = it.skinColors,
                eyeColors = it.eyeColors,
                hairColors = it.hairColors,
                links = it.links,
                url = it.url,
                isFavorite = it.isFavorite
            )
        }
    }
}

fun RoomSpecie.toDomainModel(): SWSpecie {
    return SWSpecie(
        id = id,
        name = name,
        image = image,
        classification = classification,
        designation = designation,
        averageHeight = averageHeight,
        averageLifespan = averageLifespan,
        skinColors = skinColors,
        eyeColors = eyeColors,
        hairColors = hairColors,
        links = links,
        url = url,
        isFavorite = isFavorite
    )
}
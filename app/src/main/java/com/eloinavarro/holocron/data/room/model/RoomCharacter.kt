package com.eloinavarro.holocron.data.room.model

import androidx.room.Entity
import com.eloinavarro.holocron.domain.SWCharacter
import com.eloinavarro.holocron.domain.SwLinkList

@Entity(tableName = "SWCharacter", primaryKeys = ["id"])
data class RoomCharacter(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val bornDate: String,
    val height: Float?,
    val weight: Int?,
    val skinColors: List<String>,
    val eyeColors: List<String>,
    val hairColors: List<String>,
    var links: List<SwLinkList>,
    val url: String,
    var isFavorite: Boolean
) {
    companion object {
        fun fromDomain(it: SWCharacter): RoomCharacter {
            return RoomCharacter(
                id = it.id,
                name = it.name,
                image = it.image,
                description = it.description,
                bornDate = it.bornDate,
                height = it.height,
                weight = it.weight,
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

fun RoomCharacter.toDomainModel(): SWCharacter {
    return SWCharacter(
        id = id,
        name = name,
        image = image,
        description = description,
        bornDate = bornDate,
        height = height,
        weight = weight,
        skinColors = skinColors,
        eyeColors = eyeColors,
        hairColors = hairColors,
        links = links,
        url = url,
        isFavorite = isFavorite
    )
}
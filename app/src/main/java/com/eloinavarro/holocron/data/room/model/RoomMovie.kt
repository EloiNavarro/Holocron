package com.eloinavarro.holocron.data.room.model

import androidx.room.Entity
import com.eloinavarro.holocron.domain.SWMovie
import com.eloinavarro.holocron.domain.SwLinkList

@Entity(tableName = "SWMovie", primaryKeys = ["id"])
data class RoomMovie(
    val id: Int,
    val name: String,
    val opening: String,
    val director: String,
    val producer: String,
    val releaseDate: String,
    val episode: Int,
    val image: String,
    var links: List<SwLinkList>,
    val url: String,
    val isFavorite: Boolean
) {
    companion object {
        fun fromDomain(it: SWMovie): RoomMovie {
            return RoomMovie(
                id = it.id,
                name = it.name,
                opening = it.opening,
                director = it.director,
                producer = it.producer,
                releaseDate = it.releaseDate,
                episode = it.episode,
                image = it.image,
                links = it.links,
                url = it.url,
                isFavorite = it.isFavorite
            )
        }
    }
}

fun RoomMovie.toDomainModel(): SWMovie {
    return SWMovie(
        id = id,
        name = name,
        opening = opening,
        director = director,
        producer = producer,
        releaseDate = releaseDate,
        episode = episode,
        image = image,
        links = links,
        url = url,
        isFavorite = isFavorite
    )
}
package com.eloinavarro.holocron.data.retrofit

import android.util.Log
import com.eloinavarro.holocron.domain.SWCharacter
import com.eloinavarro.holocron.domain.SWCharacterColors
import com.eloinavarro.holocron.domain.SwCharacterAppearance
import com.eloinavarro.holocron.domain.SwLink
import com.eloinavarro.holocron.domain.SwLinkType
import java.net.URLEncoder

data class SwapiResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<SwapiCharacter>
)

data class SwapiCharacter(
    val birth_year: String,
    val created: String,
    val edited: String,
    val eye_color: String,
    val films: List<String>,
    val gender: String,
    val hair_color: String,
    val height: String,
    val homeworld: String,
    val mass: String,
    val name: String,
    val skin_color: String,
    val species: List<String>,
    val starships: List<String>,
    val url: String,
    val vehicles: List<String>
)

fun SwapiCharacter.toDomainModel(): SWCharacter {
    val id = url.substringBeforeLast("/", "").substringAfterLast("/")
    val encodedName = URLEncoder.encode(name,"UTF-8")
    return SWCharacter(
        id = id,
        name = name,
        description = "",
        image = "https://via.placeholder.com/500x500/${randomColor(id.toInt())}/FFFFFF?text=$encodedName",
        bornDate = birth_year,
        appearance = SwCharacterAppearance(
            height = height.toFloatOrNull()?.div(100),
            weight = mass.toIntOrNull(),
            colors = SWCharacterColors(eye_color, hair_color, skin_color)
        ),
        links = films.map { SwLink(it, SwLinkType.MOVIE) } +
                species.map { SwLink(it, SwLinkType.SPECIE) } +
                vehicles.map { SwLink(it, SwLinkType.VEHICLE) } +
                starships.map { SwLink(it, SwLinkType.STARSHIP) },
        url = url
    )
}

private fun randomColor(id: Int):String {
    return when(id%5) {
        0 -> "309AC7" //Blue
        1 -> "21A667" //Green
        2 -> "F5DF39" //Yellow
        3 -> "E16D40" //Orange
        else -> "B66FB6" //Purple
    }
}
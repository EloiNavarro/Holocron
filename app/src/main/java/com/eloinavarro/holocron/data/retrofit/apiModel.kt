package com.eloinavarro.holocron.data.retrofit

import com.eloinavarro.holocron.domain.SWCharacter

data class ApiSwCharacter(
    val _id: String,
    val name: String,
    val description: String,
    val image: String,
    val __v: Int
)

data class ApiCallInfo (val total: Int, val page :Int, val limit:Int, val next: String?, val prev: String?)

data class SwCharactersResult(val info: ApiCallInfo, val data: List<ApiSwCharacter>)

fun ApiSwCharacter.toDomainModel(): SWCharacter {
    return SWCharacter(
        id = _id,
        name = name,
        description = description,
        image = image
    )
}
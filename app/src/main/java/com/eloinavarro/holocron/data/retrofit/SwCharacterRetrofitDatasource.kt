package com.eloinavarro.holocron.data.retrofit

import com.eloinavarro.holocron.domain.SWCharacter

class SwCharacterRetrofitDatasource constructor() :
    RetrofitDatasource<ApiSwCharacter, SWCharacter>() {

    private val url = "https://starwars-databank-server.vercel.app/api/v1/"

    private fun getApi(): SWDatabankApi {
        return RetrofitClient.getRetrofit(url)!!.create(SWDatabankApi::class.java)
    }

    suspend fun getAllCharacters(page: Int, limit: Int): List<SWCharacter> {
        return getApi().getAllCharacters(page, limit).data.map { it.toDomainModel() }
    }

    suspend fun getCharacterById(id: String):SWCharacter {
        return getApi().getCharacterById(id).toDomainModel()
    }
}
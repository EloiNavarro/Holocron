package com.eloinavarro.holocron.data.retrofit

import com.eloinavarro.holocron.domain.SWCharacter

class SwCharacterRetrofitDatasource constructor(private val mapper: ApiSwCharacterMapper) :
    RetrofitDatasource<ApiSwCharacter, SWCharacter>(mapper) {

    private val url = "https://starwars-databank-server.vercel.app/api/v1/"

    private fun getApi(): SWDatabankApi {
        return RetrofitClient.getRetrofit(url)!!.create(SWDatabankApi::class.java)
    }

    suspend fun getAllCharacters(page: Int, limit: Int): List<SWCharacter> {
        return getApi().getAllCharacters(page, limit).map { mapper.map(it) }
    }
}
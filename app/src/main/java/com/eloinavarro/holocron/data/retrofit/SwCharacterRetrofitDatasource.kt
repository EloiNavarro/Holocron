package com.eloinavarro.holocron.data.retrofit

import com.eloinavarro.holocron.domain.SWCharacter

class SwCharacterRetrofitDatasource :
    RetrofitDatasource<ApiSwCharacter, SWCharacter>() {

    private val url = "https://starwars-databank-server.vercel.app/api/v1/"

    private fun getApi(): SWDatabankApi {
        return RetrofitClient.getRetrofit(url)!!.create(SWDatabankApi::class.java)
    }

    suspend fun getAllCharacters(page: Int, limit: Int): Result<List<SWCharacter>> {
        val response = getApi().getAllCharacters(page, limit)
        return if(response.isSuccessful && response.body() != null) {
            Result.success(response.body()!!.data.map { it.toDomainModel() })
        } else {
            Result.failure(Exception("Error getAllCharacters"))
        }
    }

    suspend fun getCharacterById(id: String):Result<SWCharacter> {
        val response = getApi().getCharacterById(id)
        return if(response.isSuccessful && response.body() != null) {
            Result.success(response.body()!!.toDomainModel())
        } else {
            Result.failure(Exception("Error getCharacterById($id)"))
        }
    }
}
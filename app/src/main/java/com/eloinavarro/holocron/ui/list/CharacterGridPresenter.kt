package com.eloinavarro.holocron.ui.list

import com.eloinavarro.holocron.data.SWCharacterRepository
import com.eloinavarro.holocron.data.retrofit.ApiSwCharacterMapper
import com.eloinavarro.holocron.data.retrofit.SwCharacterRetrofitDatasource
import com.eloinavarro.holocron.domain.SWCharacter
import com.eloinavarro.holocron.domain.usecase.GetAllCharacters

class CharacterGridPresenter(private val view: CharacterGridContract.View) :
    CharacterGridContract.Actions {
    private var currentPage = 1
    private var currentLimit = 50
    private val getAllCharacters = GetAllCharacters(
        repository = SWCharacterRepository(
            apiDatasource = SwCharacterRetrofitDatasource(
                mapper = ApiSwCharacterMapper()
            )
        )
    )

    override fun setup() {
        getCurrentPageOfCharacters()
    }

    private fun getCurrentPageOfCharacters() {
        getAllCharacters
            .page(currentPage)
            .limit(currentLimit)
            .onComplete {
                view.populateCharacters(it)
            }
            .onCancel {

            }.onError {
                it.printStackTrace()
            }.execute()
    }

    private fun getNextPage() {
        currentPage++
        getCurrentPageOfCharacters()
    }

    override fun onCharacterTapped(swCharacter: SWCharacter) {
        TODO("Not yet implemented")
    }

    override fun endOfPageReached() {
        TODO("Not yet implemented")
    }
}

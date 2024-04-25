package com.eloinavarro.holocron.ui.screens.starships

import androidx.lifecycle.viewModelScope
import com.eloinavarro.holocron.data.repositories.SWMovieRepository
import com.eloinavarro.holocron.data.repositories.SWStarshipRepository
import com.eloinavarro.holocron.data.retrofit.SwapiRetrofitDatasource
import com.eloinavarro.holocron.domain.SWMovie
import com.eloinavarro.holocron.domain.SWStarship
import com.eloinavarro.holocron.domain.usecase.GetMoviesUseCase
import com.eloinavarro.holocron.domain.usecase.GetStarshipsUseCase
import com.eloinavarro.holocron.ui.common.list.ListViewModel
import kotlinx.coroutines.launch

class StarshipListViewModel : ListViewModel<SWStarship>() {

    //TODO: Add Hilt and change this
    override val useCase = GetStarshipsUseCase(
        repository = SWStarshipRepository(
            apiDatasource = SwapiRetrofitDatasource()
        )
    )

    init {
        viewModelScope.launch {
            loadNextPage()
        }
    }
}
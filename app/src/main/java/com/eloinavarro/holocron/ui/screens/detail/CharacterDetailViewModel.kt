package com.eloinavarro.holocron.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.eloinavarro.holocron.data.repositories.SWCharacterRepository
import com.eloinavarro.holocron.data.retrofit.SwapiRetrofitDatasource
import com.eloinavarro.holocron.domain.SWCharacter
import com.eloinavarro.holocron.domain.usecase.GetCharacterByIdUseCase
import com.eloinavarro.holocron.ui.common.detail.DetailViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    override val id: Int
) : DetailViewModel<SWCharacter>() {

    override val useCase = GetCharacterByIdUseCase(
        repository = SWCharacterRepository(
            apiDatasource = SwapiRetrofitDatasource()
        )
    )

    init {
        viewModelScope.launch {
            useCase(id).onSuccess { item ->
                uiStateFlow.update { it.copy(loading = true) }
                uiStateFlow.update {
                    it.copy(
                        item = item,
                        loading = false
                    )
                }
            }.onFailure {
                // TODO
            }
        }
    }
}

class CharacterDetailViewModelFactory(val id: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterDetailViewModel(id) as T
    }
}
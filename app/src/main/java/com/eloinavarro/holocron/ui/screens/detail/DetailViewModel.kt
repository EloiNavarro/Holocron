package com.eloinavarro.holocron.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.eloinavarro.holocron.data.repositories.SWCharacterRepository
import com.eloinavarro.holocron.data.retrofit.SwapiRetrofitDatasource
import com.eloinavarro.holocron.domain.SWCharacter
import com.eloinavarro.holocron.domain.usecase.GetCharacterByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(private val id: Int) : ViewModel() {

    //TODO: Add Hilt and change this
    private val getCharacterByIdUseCase = GetCharacterByIdUseCase(
        repository = SWCharacterRepository(
            apiDatasource = SwapiRetrofitDatasource()
        )
    )

    var uiStateFlow = MutableStateFlow(UIState())
        private set

    init {
        viewModelScope.launch {
            uiStateFlow.update { it.copy(loading = true) }
            uiStateFlow.update {
                it.copy(
                    character = getCharacterByIdUseCase(id).getOrNull(),
                    loading = false
                )
            }
        }
    }

    data class UIState(
        val loading: Boolean = false,
        val character: SWCharacter? = null
    )
}

class DetailViewModelFactory(val id: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(id) as T
    }
}
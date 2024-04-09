package com.eloinavarro.holocron.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.eloinavarro.holocron.data.SWCharacterRepository
import com.eloinavarro.holocron.data.retrofit.SwCharacterRetrofitDatasource
import com.eloinavarro.holocron.domain.SWCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(private val id: String) : ViewModel() {

    private val characterRepository = SWCharacterRepository(
        apiDatasource = SwCharacterRetrofitDatasource()
    )

    var uiState = MutableStateFlow(UIState())
        private set

    init {
        viewModelScope.launch {
            uiState.update { it.copy(loading = true) }
            uiState.update {
                it.copy(
                    character = characterRepository.getCharacterById(id).getOrNull(),
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

class DetailViewModelFactory(val id: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(id) as T
    }
}
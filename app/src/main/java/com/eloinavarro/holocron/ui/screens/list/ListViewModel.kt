package com.eloinavarro.holocron.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.eloinavarro.holocron.data.SWCharacterRepository
import com.eloinavarro.holocron.data.retrofit.SwCharacterRetrofitDatasource
import com.eloinavarro.holocron.domain.SWCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    private var currentPage = 1
    private var currentLimit = 50
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
                    characters = characterRepository.getAllCharacters(currentPage, currentLimit),
                    loading = false
                )
            }
        }
    }

    data class UIState(
        val loading:Boolean = false,
        val characters: List<SWCharacter> = emptyList()
    )
}

// Not needed here, just when ListViewModel has parameters
class ListViewModelFactory: ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>):T{
        return ListViewModel() as T
    }
}
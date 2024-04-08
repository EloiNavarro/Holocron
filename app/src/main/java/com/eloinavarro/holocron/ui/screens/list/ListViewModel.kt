package com.eloinavarro.holocron.ui.screens.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.eloinavarro.holocron.data.SWCharacterRepository
import com.eloinavarro.holocron.data.retrofit.SwCharacterRetrofitDatasource
import com.eloinavarro.holocron.domain.SWCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    private var currentPage = 1
    private var currentLimit = 50
    private val characterRepository = SWCharacterRepository(
        apiDatasource = SwCharacterRetrofitDatasource()
    )

    private val _uistate = MutableStateFlow(UIState())
    val uistate: StateFlow<UIState> = _uistate.asStateFlow()

    var state by mutableStateOf(UIState())
        private set


    init {
        viewModelScope.launch {
            _uistate.update { it.copy(loading = true) }
            _uistate.update {
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
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

    private var currentLimit = 50
    private val characterRepository = SWCharacterRepository(
        apiDatasource = SwCharacterRetrofitDatasource()
    )

    var uiState = MutableStateFlow(UIState())
        private set

    private val paginator = DefaultPaginator (
        initialKey = uiState.value.page,
        onLoadUpdated = { isUpdated ->
            uiState.update { it.copy(loading = isUpdated) }
        },
        onRequest = { nextPage ->
            characterRepository.getAllCharacters(nextPage, currentLimit)
        },
        getNextKey = { items ->
            uiState.value.page + 1
        },
        onError = { error ->
            uiState.update {
                it.copy(
                    error = error?.localizedMessage
                )
            }
        },
        onSuccess = { newItems, newKey ->
            uiState.update {
                it.copy(
                    characters = uiState.value.characters + newItems,
                    page = newKey,
                    endReached = newItems.isEmpty()
                )
            }
        }
    )

    fun loadNextPage() {
        viewModelScope.launch {
            paginator.loadNextPage()
        }
    }

    init {
        viewModelScope.launch {
            paginator.loadNextPage()
        }
    }

    data class UIState(
        val loading: Boolean = false,
        val characters: List<SWCharacter> = emptyList(),
        val error: String? = null,
        val endReached: Boolean = false,
        val page: Int = 1
    )
}

// Not needed here, just when ListViewModel has parameters
class ListViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListViewModel() as T
    }
}
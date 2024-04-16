package com.eloinavarro.holocron.ui.common.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eloinavarro.holocron.domain.SWItem
import com.eloinavarro.holocron.domain.usecase.UseCase
import com.eloinavarro.holocron.ui.common.Paginated
import com.eloinavarro.holocron.ui.common.SwPaginator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class ListViewModel<T : SWItem> : ViewModel(), Paginated {

    abstract val useCase: UseCase.Multiple<T>

    var uiStateFlow = MutableStateFlow(ListUiState<T>())
        private set

    private val onLoadUpdated: (Boolean) -> Unit = { isUpdated ->
        uiStateFlow.update { it.copy(loading = isUpdated) }
    }
    private val onRequest: suspend (Int) -> Result<List<T>> = { nextPage ->
        useCase(nextPage)
    }
    private val getNextPage: suspend (Int) -> Int = { currentKey ->
        currentKey + 1
    }
    private val onError: suspend (Throwable?) -> Unit = { error ->
        uiStateFlow.update {
            it.copy(
                error = error?.localizedMessage
            )
        }
    }
    private val onSuccess: suspend (List<T>, Int) -> Unit = { newItems, newKey ->
        uiStateFlow.update {
            it.copy(
                items = (uiStateFlow.value.items + newItems),
                page = newKey,
                endReached = newItems.isEmpty()
            )
        }
    }

    private val paginator = SwPaginator(
        initialPage = uiStateFlow.value.page,
        isLoading = onLoadUpdated,
        onRequest = onRequest,
        getNextPage = getNextPage,
        onError = onError,
        onSuccess = onSuccess
    )

    override fun loadNextPage() {
        viewModelScope.launch {
            paginator.loadNextPage()
        }
    }
}
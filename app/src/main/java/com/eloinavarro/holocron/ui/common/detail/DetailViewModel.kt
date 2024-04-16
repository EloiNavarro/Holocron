package com.eloinavarro.holocron.ui.common.detail

import androidx.lifecycle.ViewModel
import com.eloinavarro.holocron.domain.SWItem
import com.eloinavarro.holocron.domain.usecase.UseCase
import kotlinx.coroutines.flow.MutableStateFlow

abstract class DetailViewModel<T : SWItem> : ViewModel() {

    abstract val id: Int
    abstract val useCase: UseCase.Item<T>

    var uiStateFlow = MutableStateFlow(DetailUiState<T>())
        private set

}
package com.eloinavarro.holocron.ui.screens.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.eloinavarro.holocron.domain.SWCharacter
import com.eloinavarro.holocron.domain.usecase.GetCharacterByIdUseCase
import com.eloinavarro.holocron.ui.common.detail.DetailViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharacterDetailViewModel(
    override val id: Int
) : DetailViewModel<SWCharacter>(), KoinComponent {

    override val useCase: GetCharacterByIdUseCase by inject()

    init {
        viewModelScope.launch {
            uiStateFlow.update { it.copy(loading = true) }
            uiStateFlow.update {
                it.copy(
                    item = useCase(id),
                    loading = false
                )
            }
        }
    }
}

class CharacterDetailViewModelFactory(val id: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterDetailViewModel(id) as T
    }
}
package com.eloinavarro.holocron.ui.screens.detail

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.eloinavarro.holocron.data.SWCharacterRepository
import com.eloinavarro.holocron.data.retrofit.SwCharacterRetrofitDatasource
import com.eloinavarro.holocron.domain.SWCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(private val id: String) : ViewModel() {

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
                    character = characterRepository.getCharacterById(id),
                    loading = false
                )
            }
        }
    }

    data class UIState(
        val loading:Boolean = false,
        val character: SWCharacter? = null
    )
}

class DetailViewModelFactory(val id:String): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>):T{
        return DetailViewModel(id) as T
    }
}
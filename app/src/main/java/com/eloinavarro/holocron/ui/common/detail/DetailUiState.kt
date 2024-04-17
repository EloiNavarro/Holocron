package com.eloinavarro.holocron.ui.common.detail

import com.eloinavarro.holocron.domain.SWItem

data class DetailUiState<T:SWItem> (
    val loading: Boolean = false,
    val item: Result<T> = Result.failure(Exception("Item not found"))
)
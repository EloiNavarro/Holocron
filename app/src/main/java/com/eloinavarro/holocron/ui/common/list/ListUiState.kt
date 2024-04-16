package com.eloinavarro.holocron.ui.common.list

import com.eloinavarro.holocron.domain.SWItem

data class ListUiState<T:SWItem>(
    val loading: Boolean = false,
    val items: List<T> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 1
)

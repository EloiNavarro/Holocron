package com.eloinavarro.holocron.ui.common

interface Paginator<Key, Item> {
    suspend fun loadNextPage()
    suspend fun reset()
}
package com.eloinavarro.holocron.ui.common

import com.eloinavarro.holocron.domain.SWItem

class SwPaginator<T: SWItem>(
    private val initialPage: Int,
    private inline val isLoading: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Int) -> Result<List<T>>,
    private inline val getNextPage: suspend (Int) -> Int,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend (items: List<T>, newKey: Int) -> Unit
){

    private var currentPage = initialPage
    private var hasNextPage = true
    private var isMakingRequest = false

    suspend fun loadNextPage() {
        if(isMakingRequest || !hasNextPage) { return }
        isMakingRequest = true
        isLoading(true)
        val result = onRequest(currentPage)
        isMakingRequest = false
        val items = result.getOrElse {
            onError(it)
            isLoading(false)
            hasNextPage = false
            return
        }
        currentPage = getNextPage(currentPage)
        onSuccess(items, currentPage)
        isLoading(false)
    }

    suspend fun reset() {
        currentPage = initialPage
        hasNextPage = true
    }

}
package com.eloinavarro.holocron.ui.common

class SwPaginator<T>(
    private val initialPage: Int,
    private inline val isLoading: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Int) -> Result<List<T>>,
    private inline val getNextPage: suspend (Int) -> Int,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend (items: List<T>, newKey: Int) -> Unit
){

    private var currentPage = initialPage
    private var isMakingRequest = false

    suspend fun loadNextPage() {
        if(isMakingRequest) { return }
        isMakingRequest = true
        isLoading(true)
        val result = onRequest(currentPage)
        isMakingRequest = false
        val items = result.getOrElse {
            onError(it)
            isLoading(false)
            return
        }
        currentPage = getNextPage(currentPage)
        onSuccess(items, currentPage)
        isLoading(false)
    }

    suspend fun reset() {
        currentPage = initialPage
    }

}
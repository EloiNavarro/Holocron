package com.eloinavarro.holocron.data.repositories

import com.eloinavarro.holocron.domain.SWItem
import java.util.TreeMap

abstract class Repository <T: SWItem> {

    private var cachedContent: List<T> = emptyList()
    private var cachedByPage: TreeMap<Int, List<T>> = TreeMap()

    internal suspend fun getAll(page:Int, getAllRemote: suspend ()-> Result<List<T>>): Result<List<T>> {
        if(!cachedByPage.containsKey(page)) {
            getAllRemote().onSuccess {
                cachedContent = cachedContent + it
                cachedByPage[page] = it
                return Result.success(it)
            }.onFailure { error ->
                return Result.failure(error)
            }
        } else {
            return Result.success(cachedByPage[page]!!)
        }
        return Result.failure(Exception("Items not found"))
    }

    internal suspend fun find(id: Int, getByIdRemote: suspend () -> Result<T>): Result<T> {
        val key = cachedByPage.entries.find {  entry ->
            entry.value.any { it.id == id }
        }?.key
        key?.let { page ->
            val item = cachedByPage[page]!!.find { it.id == id }
            if(item != null)  return Result.success(item)
        }
        getByIdRemote().onSuccess {
            return Result.success(it)
        }.onFailure {
            return Result.failure(it)
        }
        return Result.failure(Exception("Item not found"))
    }
}
package com.eloinavarro.holocron.domain.usecase

import android.util.Log
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class UseCase<T> {
    private var parentJob: Job = Job()
    private var onComplete: ((T) -> Unit)? = null
    private var onError: ((Throwable) -> Unit)? = null
    private var onCancel: ((CancellationException) -> Unit)? = null

    private var backgroundContext: CoroutineContext = Dispatchers.IO
    private var foregroundContext: CoroutineContext = Dispatchers.Main

    protected abstract suspend fun executeOnBackground(): T

    fun execute() {
        Log.d("DEBUG", "execute use case")
        unsubscribe()
        parentJob = Job()
        CoroutineScope(foregroundContext + parentJob).async {
            try {
                val result = withContext(backgroundContext) {
                    executeOnBackground()
                }
                onComplete?.invoke(result)
            } catch (cancellationException: CancellationException) {
                onCancel?.invoke(cancellationException)
            } catch (e: Exception) {
                onError?.invoke(e)
            }
        }
    }

    protected suspend fun <X> runAsync(
        context: CoroutineContext = backgroundContext,
        block: suspend () -> X
    ): Deferred<X> {
        return CoroutineScope(context + parentJob).async {
            block.invoke()
        }
    }

    private fun unsubscribe() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }

    fun onComplete(block: (T) -> Unit): UseCase<T> {
        onComplete = block
        return this
    }

    fun onError(block: (Throwable) -> Unit): UseCase<T> {
        onError = block
        return this
    }

    fun onCancel(block: (CancellationException) -> Unit): UseCase<T> {
        onCancel = block
        return this
    }
}
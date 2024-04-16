package com.eloinavarro.holocron.domain.usecase

interface UseCase {
    interface Multiple<T> {
        suspend operator fun invoke(page: Int): Result<List<T>>
    }
    interface Item<T> {
        suspend operator fun invoke(id: Int): Result<T>
    }
}
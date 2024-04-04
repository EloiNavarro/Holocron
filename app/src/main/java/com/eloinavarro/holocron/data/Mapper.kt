package com.eloinavarro.holocron.data

interface Mapper<I,O> {
    fun map(input: I): O
}
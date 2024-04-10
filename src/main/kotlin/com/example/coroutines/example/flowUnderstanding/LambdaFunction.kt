package com.example.coroutines.example.flowUnderstanding

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class LambdaFunction {
    private fun <T> Flow<T>.filter (
        predicate: (value: T) -> Boolean
    ): Flow<T> = flow {
        collect { value ->
            if (predicate(value)) emit(value)
        }
    }

    private fun <T> Flow<T>.onEach (
        action: (value: T) -> Unit
    ): Flow<T> = flow {
        collect { value ->
            action(value)
            emit(value)
        }
    }

    private fun <T> Flow<T>.onStart (
        action: suspend () -> Unit
    ): Flow<T> = flow {
        action()
        collect { value ->
            emit(value)
        }
    }

    suspend fun main() {
        val l = flowOf(1, 2, 3, 4, 5)

        l.filter { it % 2 == 0 }
            .collect { println("Collected $it") }

        l.onEach { println("Processing $it") }
            .collect { println("Collected $it") }

        l.onStart { println("Start") }
            .collect { println("Collected $it") }
    }
}
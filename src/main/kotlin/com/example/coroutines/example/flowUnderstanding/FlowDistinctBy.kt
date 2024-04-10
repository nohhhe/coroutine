package com.example.coroutines.example.flowUnderstanding

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class FlowDistinctBy {
    private fun <T, K> Flow<T>.distinctBy (
        keySelector: (T) -> K
    ) = flow {
        val sentKey = mutableSetOf<K>()
        collect { value ->
            val key = keySelector(value)
            if (key !in sentKey) {
                sentKey.add(key)
                emit(value)
            }
        }
    }

    suspend fun main() {
        val l = flowOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        l.distinctBy { it % 3 }
            .collect { println("Collected $it") }
    }
}
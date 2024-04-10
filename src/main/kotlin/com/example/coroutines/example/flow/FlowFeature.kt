package com.example.coroutines.example.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FlowFeature {
    private fun usersFlow(): Flow<String> = flow {
        repeat(3) {
            delay(1000L)
            val ctx = currentCoroutineContext()
            val name = ctx[CoroutineName]?.name
            emit("User$it in $name")
        }
    }

    suspend fun main() {
        val users = usersFlow()

        withContext(CoroutineName("Name")) {
            val job = launch {
                users.collect {
                    println(it)
                }
            }

            launch {
                delay(2500L)
                println("I got enough!")
                job.cancel()
            }
        }
    }
}
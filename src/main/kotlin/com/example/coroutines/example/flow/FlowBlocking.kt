package com.example.coroutines.example.flow

import kotlinx.coroutines.*

class FlowBlocking {
    private fun getSequence(): Sequence<String> = sequence {
        repeat(3) {
            Thread.sleep(1000L)
            yield("User$it")
        }
    }

    suspend fun main() {
        coroutineScope {
            launch {
                repeat(3) {
                    delay(100L)
                    println("Processing on coroutine")
                }
            }

            val list = getSequence()
            list.forEach {
                println("S: $it")
            }
        }
    }
}
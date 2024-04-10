package com.example.coroutines.example

import kotlinx.coroutines.*

class CoroutineScopeFunction {
    suspend fun main(): Unit = coroutineScope {
        launch(CoroutineName("main")) { // 메인 코루틴
            launch(CoroutineName("child1")) { // 1번 코루틴
                delay(2000L)
                println("will not be printed")
            }

            /*withTimeout(1000L) { // withTimeout 코루틴
                try {
                    delay(1500L)
                } catch (e: TimeoutCancellationException) {
                    println("내부 Caught $e")
                }
            }

            try {
                withTimeout(1000L) { // withTimeout 코루틴
                    try {
                        delay(1500L)
                    } catch (e: TimeoutCancellationException) {
                        println("내부 Caught $e")
                    }
                }
            } catch (e: TimeoutCancellationException) {
                println("외부 Caught $e")
            }*/
        }
        launch(CoroutineName("child3")) { // 3번 코루틴
            delay(2000L)
            println("Done")
        }
    }
}
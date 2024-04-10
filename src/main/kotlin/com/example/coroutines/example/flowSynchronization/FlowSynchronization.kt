package com.example.coroutines.example.flowSynchronization

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.random.Random

class FlowSynchronization {
    private fun Flow<*>.counter(): Flow<Int> {
        var count = 0
        println("start $count")

        return this.map {
            count++
            if(count % 500 == 0) println("${currentCoroutineContext()[CoroutineName]} - map $count, this: $this" )
            List(100) { Random.nextLong() }.shuffled().sorted()
            count
        }
    }

    /*private fun Flow<*>.counter() = flow {
        var counter = 0
        println("start $counter")

        collect {
            counter++
            if(counter >= 1000) println("map $counter")
            List(100) { Random.nextLong() }.shuffled().sorted()
            emit(counter)
        }
    }*/

    suspend fun main(): Unit = coroutineScope {
        val f1 = List(1000) { "$it" }.asFlow()
        val f2 = List(1000) { "$it" }.asFlow().counter()

        launch(CoroutineName("f1_1")) { println(f1.counter().last()) }
        launch(CoroutineName("f1_2")) { println(f1.counter().last()) }
        launch(CoroutineName("f2_1")) { println(f2.last()) }
        launch(CoroutineName("f2_2")) { println(f2.last()) }
    }
}
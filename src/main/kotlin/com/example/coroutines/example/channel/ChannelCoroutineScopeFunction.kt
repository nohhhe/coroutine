package com.example.coroutines.example.channel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChannelCoroutineScopeFunction {
    suspend fun main(): Unit = coroutineScope {
        val channel = Channel<Int>()
        launch(Dispatchers.IO.limitedParallelism(2)) {
            repeat(5) { index ->
                delay(1000)
                println("send $index")
                channel.send(index * 2)
            }
        }

        launch {
            repeat(5) {
                val received = channel.receive()
                println("received $received")
            }
        }
    }
}
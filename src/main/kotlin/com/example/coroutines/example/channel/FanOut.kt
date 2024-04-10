package com.example.coroutines.example.channel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FanOut {
    private fun CoroutineScope.produceNumbers() = produce {
        repeat(10) {
            delay(100)
            send(it)
        }
    }

    private fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch {
        /*for (msg in channel) {
            if(id == 1) delay(1000)
            println("Processor #$id received $msg")
        }*/

        channel.consumeEach {
            if(id == 1) delay(1000)
            println("Processor #$id received $it")
        }
    }

    suspend fun main(): Unit = coroutineScope {
        val channel = produceNumbers()
        repeat(3) { id ->
            delay(10)
            launchProcessor(id, channel)
        }
    }
}
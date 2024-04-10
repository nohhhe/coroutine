package com.example.coroutines

import com.example.coroutines.example.flowSynchronization.FlowSynchronization
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoroutinesApplication

suspend fun main(args: Array<String>) {
    runApplication<CoroutinesApplication>(*args)

    FlowSynchronization().main()
}

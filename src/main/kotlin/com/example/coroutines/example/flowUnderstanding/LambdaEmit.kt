package com.example.coroutines.example.flowUnderstanding

class LambdaEmit {

   /* suspend fun main() {
        val f: suspend ((String) -> Unit) -> Unit = { emit ->
            emit("A")
            emit("B")
            emit("C")
        }

        f { print(it) }
        f { print(it) }
    }*/

    fun interface FlowCollector {
        fun emit(value: String)
    }

    fun main() {
        val f: FlowCollector.() -> Unit = {
            emit("A")
            emit("B")
            emit("C")
        }

        f { print(it) }
        f { print(it) }
    }
}
package com.example.coroutines.example.hotChannelColdFlow

class HotChannelColdFlowCompare {
    fun main() {
        val l = buildList {
            repeat(3) {
                add("User$it")
                println("L: Added User")
            }
        }

        val l2 = l.map {
            println("L: Processing")
            "Processed $it"
        }

        val s = sequence {
            repeat(3) {
                yield("User$it")
                println("S: Added User")
            }
        }

        val s2 = s.map {
            println("S: Processing")
            "Processed $it"
        }

        s2.forEach {
            println("S: $it")
        }
    }

    fun m(i: Int):Int {
        print("m$i ")
        return i * i
    }

    fun main2() {
        val l = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .map { m(it) }

        println(l)
        println(l.find { it > 10 })
        println(l.find { it > 10 })
        println(l.find { it > 10 })

        val s = sequenceOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .map { m(it) }

        println(s.toList())
        println(s.find { it > 10 })
        println(s.find { it > 10 })
        println(s.find { it > 10 })
    }
}
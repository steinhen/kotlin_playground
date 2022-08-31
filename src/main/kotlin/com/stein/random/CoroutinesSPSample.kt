package com.stein.random

import kotlinx.coroutines.*

fun main() {
    var launch: Job? = null
    try {
        launch = GlobalScope.launch {
            try {
                println("Started coroutine 1")
                var i = 0
                while (!Test.state) {
                    println("running coroutine 1: ${i++}")
                    delay(1000)
                }
                println("Coroutine 1 ended")
            } finally {
                println("Internal catch")
            }
        }
    } catch(e: Exception) {
        println("External catch")
    }

    val launch1 = GlobalScope.launch {
        println("Run coroutine 2")
    }

    runBlocking {
        repeat(5) {
            delay(1000)
//            println("Main delay $it")
        }
        println("Changed state")
        launch?.cancelAndJoin()
//        Test.state = true
        delay(2000)
    }

    println("end")
}

object Test {
    var state: Boolean = false
}
package com.stein.guide

import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {
        delay(2000L)
        println("Task from runBlocking")
    }
    coroutineScope { // Creates a coroutine scope
        launch {
            delay(5000L)
            println("Task from nested launch")
        }

        delay(1000L)
        println("Task from coroutine scope") // This line will be printed before the nested launch
    }
    println("Coroutine scope is over")
}


package com.stein.guide

import kotlinx.coroutines.*

fun main() {
    runBlocking {

        launch(Dispatchers.IO) {
            println("something")
            delay(5000)
//            throw RuntimeException("Error")
        }
        launch(Dispatchers.IO) {
            println("ann")
            delay(5000)
//            throw RuntimeException("Error")
        }

        println("2")


    }
    runBlocking {
        (1..3).map {
            println(it)
            delay(1000)

        }
    }
}
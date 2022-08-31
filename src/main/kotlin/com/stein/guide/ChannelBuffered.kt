package com.stein.guide

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

fun main() {
    runBlocking {
        val channel = Channel<Int>(4) // create buffered channel
        launch { // launch sender coroutine
            repeat(10) {
                println("Sending $it") // print before sending each element
                channel.send(it) // will suspend when buffer is full
            }
        }
        // don't receive anything... just wait....
//        delay(1000)
        launch {
            repeat(10) {
                delay(1000)
                channel.receive()
            }
        }
    }
}
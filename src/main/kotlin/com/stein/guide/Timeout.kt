package com.stein

import kotlinx.coroutines.*

fun main() = runBlocking {
    withTimeoutOrNull(1300L) {
        repeat(100) {
            println("I'm sleeping $it")
            delay(500L)
        }
    }

    try {
        withTimeout(1300L) {
            repeat(100) {
                println("I'm sleeping $it")
                delay(500L)
            }
        }
    } catch (e: CancellationException) {
        println("Cancellation Ex caught!")
    }

    Unit
}
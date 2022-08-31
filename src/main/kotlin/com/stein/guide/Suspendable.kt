package com.stein

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

fun main() {
    runBlocking {
        doSomething()
        withTimeout(2000) {
            println("finished")
        }
    }
}

private suspend fun doSomething() {
    delay(3000)
    println("did something")
}
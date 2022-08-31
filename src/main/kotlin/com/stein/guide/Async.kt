package com.stein.guide

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val time = measureTimeMillis {
        val a = async { doSomethingOne() }
        val b = async { doSomethingTwo() }
        println("Total is ${a.await() + b.await()}")
    }
    println("Time to run: $time")
}

private suspend fun doSomethingOne(): Int {
    delay(1000)
    return 1
}

private suspend fun doSomethingTwo(): Int {
    delay(1000)
    return 2
}

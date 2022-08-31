package com.stein

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun simpleFlow(): Flow<Int> = flow {
    println("Flow started")
    for (i in 1..3) {
        emit(i)
        delay(1000)
    }
}

fun main() = runBlocking {
    println("Calling simple function...")
    val flow = simpleFlow()
    println("Calling collect...")
    flow.collect { value -> println(value) }
    println("Calling collect again...")
    flow.collect { value -> println(value) }
}
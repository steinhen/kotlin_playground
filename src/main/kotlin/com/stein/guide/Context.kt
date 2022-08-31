package com.stein

import kotlinx.coroutines.*

fun main() = runBlocking {
    println("My job is ${coroutineContext[Job]}")
}
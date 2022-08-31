package com.stein.guide

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    val message = Client()
    println(message)

    GlobalScope.launch {
        delay(30)
        message.value.add(1)
        println(message)
    }

    GlobalScope.launch {
        message.value.add(2)
        println(message)
    }
    Thread.sleep(200)
}

data class Client(
    val name: String = "a",
    val value: MutableSet<Int> = mutableSetOf()
)
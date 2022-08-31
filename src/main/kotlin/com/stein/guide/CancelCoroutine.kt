package com.stein

import kotlinx.coroutines.*


fun main() = runBlocking {
    val job = launch {
        repeat(1000) { i ->
            println("OOOOOOOOOOO $i ...")
            delay(500L)
        }
    }
    val job2 = launch(Dispatchers.IO) {
        repeat(1000) { i ->
            println("----------- $i ...")
            delay(500L)
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")

    job.cancelAndJoin() // cancels the job
    job.join() // waits for job's completion
    job2.cancelAndJoin() // cancels the job
    job2.join() // waits for job's completion

    println("main: Now I can quit.")
}


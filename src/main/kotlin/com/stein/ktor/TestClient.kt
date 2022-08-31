package com.stein.ktor

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis


class TestClient {
    private val client = HttpClient(CIO) {
        engine {
            threadsCount = 10
            maxConnectionsCount = 1000
            requestTimeout = 100
        }
    }

    suspend fun test(): Long {
        return withTimeout(3000) {
            measureTimeMillis {
                client.get("https://kotlinlang.org/docs/coroutines-basics.html#an-explicit-job")
            }
        }
    }
}

fun main() {
    val testClient = TestClient()
    measureTimeMillis {
        runBlocking {
            var timedout = 0
            var complete = 0
            val tasks = mutableListOf<Deferred<Long?>>()

            repeat(1) {
                repeat(1) {
                    tasks.add(
                        async {
                            try {
                                val time = testClient.test()
                                complete++
                                return@async time
                            } catch (e: Exception) {
                                timedout++
                                return@async null
                            }
                        }
                    )
                }
            }

            val list = tasks.awaitAll()
                .filterNotNull()

            val avg = list.takeIf { it.isNotEmpty() }?.reduce { acc, n -> acc + n }?.div(list.size)

            println("timeout:$timedout")
            println("complete:$complete")
            println("avg:$avg")
            println("top:${list.maxOrNull()}")
            println("lowest:${list.minOrNull()}")

        }
    }.apply { println("TOTAL TIME:$this") }
}
package com.stein.guide

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.selects.select
import org.slf4j.LoggerFactory
import kotlin.random.Random



fun main() {
    val logger = LoggerFactory.getLogger("MAIN")

    runBlocking {
        logger.info("START")

        val tasks = Channel<Item>()
        val commit = Channel<Item>()
        val reject = Channel<Item>()
        val finished = Channel<Item>()

        downloader(tasks, commit, reject)
        postProcess(commit, reject, finished)

        launch {
            for (task in 1..1) {
                (1..5).map {
                    async {
                        val item = Item(it, "url-$it")
                        logger.debug("Produced $item")
                        tasks.send(item)

                    }
                }.awaitAll()
                (1..5).map {

                    logger.debug("${finished.receive()} done")
                }
                logger.debug("**** Batch processed ****")
                delay(5000)
            }
        }

    }
}

fun CoroutineScope.downloader(
    tasks: ReceiveChannel<Item>,
    commit: SendChannel<Item>,
    reject: SendChannel<Item>
) {
    repeat(5) {
        launch {
            val logger = LoggerFactory.getLogger("Downloader$it")
            for (task in tasks) {
//                    delay(500)
                logger.info("Received $task")
                if (Random.nextBoolean()) {
                    val delay = Random.nextInt(0, 10)
                    logger.debug("$task processing will take $delay")
                    delay(delay * 1000L)
                    logger.debug("Finished processing $task")
                    commit.send(task)
                } else {
                    reject.send(task)
                }
            }
        }
    }
}

fun CoroutineScope.postProcess(
    commit: ReceiveChannel<Item>,
    reject: ReceiveChannel<Item>,
    finished: SendChannel<Item>
) {
    repeat(3) {
        launch {
            val logger = LoggerFactory.getLogger("PostProcessor$it")
            while (!commit.isClosedForReceive || !reject.isClosedForReceive) {
                select<Unit> {
                    commit.onReceive {
//                            delay(500)
                        logger.info("Persisting $it")
                        finished.send(it)
                    }
                    reject.onReceive {
//                            delay(500)
                        logger.error("$it failed!")
                        finished.send(it)
                    }
                }
            }
        }
    }
}

data class Item(
    val id: Int,
    val url: String
)

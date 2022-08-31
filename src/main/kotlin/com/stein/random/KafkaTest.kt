package com.stein.random

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.kstream.Consumed
import org.apache.kafka.streams.kstream.Produced
import java.util.*
import kotlin.random.Random

val topic = "seven"
val host = "localhost:9092"
val appId = "appId"
val groupId = "groupId"

fun main(): Unit = runBlocking {
    GlobalScope.launch { produce() }

    println("++++++++++++++++ Starting consumer")
    consume()
}

private fun consume() {
    val streamProps = Properties()
    streamProps["bootstrap.servers"] = host
    streamProps["application.id"] = appId
    streamProps["group.id"] = groupId
    streamProps[StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG] = Serdes.String()::class.java
    streamProps[StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG] = Serdes.String()::class.java
    streamProps[StreamsConfig.COMMIT_INTERVAL_MS_CONFIG] = 500

    val streamsBuilder = StreamsBuilder()

    val wordCounts = streamsBuilder
        .stream(topic, Consumed.with(Serdes.String(), Serdes.String()))
        .peek { _, value -> println("Counting words in [$value]") }
        .flatMapValues { value -> value.trim().split(" ") }
        .groupByKey()
        .count()

    wordCounts.toStream()
        .mapValues { v -> v.toString() }
        .to("${topic}_output", Produced.with(Serdes.String(), Serdes.String()))

    KafkaStreams(streamsBuilder.build(), streamProps).start()
}

private suspend fun produce() {
    val producerProps = Properties()
    producerProps["bootstrap.servers"] = host
    producerProps["application.id"] = appId
    producerProps["group.id"] = groupId
    producerProps["key.serializer"] = StringSerializer::class.java
    producerProps["value.serializer"] = StringSerializer::class.java

    val kafkaProducer = KafkaProducer<String, String>(producerProps)
    for (i in 0..1000) {
        delay(100)
        val s = StringBuilder()
        repeat(Random.nextInt(0, 10)) { s.append("word ") }
        println("Producing: $s")
        kafkaProducer.send(
            ProducerRecord(topic, i.toString(), s.toString())
        ) { _, exception -> println(exception.message) }
    }
    kafkaProducer.close()
}
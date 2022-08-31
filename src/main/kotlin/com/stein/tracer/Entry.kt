package com.stein.tracer

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun main() {

    // creates the tracer
    val tracer = when ("E") {
        "L" -> LogEnhancedTracer(EnhancedTracer("Execution With Log Tracer"))
        "E" -> EnhancedTracer("Execution With Enhanced Tracer")
        else -> SimpleTracer("Execution With Simple")
    }

    val b = B()

    // mimic something like enrichment overly simplified
    with(Params("event 1", "event 2")) {
        a(this, tracer)
        runBlocking { delay(200) }
        b.b(this, tracer)
    }

    tracer.finish()

    // print message to check finish is non-blocking
    println("end!")

    // waits for finish (should be done differently but needs to be checked in actual code)
    runBlocking { delay(10000) }

    // logs real end
    println("game over!")
}

/**
 * Lazy tracer. Would save all at the finish
 */
interface ITracer {
    fun register(event: String)
    fun finish()
}

/**
 * base class for laze tracer
 */
abstract class Tracer<T : Trace>(val name: String) : ITracer {

    abstract fun getList(): MutableList<T>
    abstract fun buildTrace(event: String): T

    override fun register(event: String) {
        getList().add(buildTrace(event))
    }

    /**
     * non blocking save
     */
    override fun finish() {
        GlobalScope.launch {
            delay(5000)
            println(this@Tracer.toString())
        }
    }

    override fun toString(): String {
        return name + ": " + getList()
    }
}

class SimpleTracer(name: String) : Tracer<Trace>(name) {
    private val events = mutableListOf<Trace>()

    override fun buildTrace(event: String): Trace = Trace(event)

    override fun getList(): MutableList<Trace> = events
}

open class Trace(private val event: String) {
    override fun toString(): String {
        return "{$event}"
    }
}

class EnhancedTracer(name: String) : Tracer<EnhancedTrace>(name) {
    private val events = mutableListOf<EnhancedTrace>()
    private var lastEntryTimestamp: Instant? = null

    override fun buildTrace(event: String): EnhancedTrace {
        val now = Instant.now()
        return EnhancedTrace(event, lastEntryTimestamp ?: now, now).also { lastEntryTimestamp = now }
    }

    override fun getList(): MutableList<EnhancedTrace> = events

}

class LogEnhancedTracer(private val tracer: Tracer<EnhancedTrace>) : Tracer<EnhancedTrace>(tracer.name) {

    override fun getList(): MutableList<EnhancedTrace> {
        return tracer.getList()
    }

    override fun buildTrace(event: String): EnhancedTrace {
        return tracer.buildTrace(event).also {
            println("LOG:$it")
        }
    }

}

class EnhancedTrace(
    private val event: String,
    private val previousTraceTimestamp: Instant?,
    private val timestamp: Instant = Instant.now()
) : Trace(event) {
    override fun toString(): String {
        return "{$event, $timestamp (${
            timestamp.minusMillis((previousTraceTimestamp ?: timestamp).toEpochMilli()).toEpochMilli()
        })}"
    }
}

data class Params(val a: String, val b: String)

private fun a(params: Params, tracer: ITracer) {
    println(params.a)
    tracer.register("register event 1")
}

class B {
    fun b(params: Params, tracer: ITracer) {
        println(params.b)
        tracer.register("register event 2")
    }
}
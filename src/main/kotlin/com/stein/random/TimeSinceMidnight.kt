package com.stein.random

private const val ONE_HOUR_IN_SECONDS = 60 * 60
private const val ONE_MINUTE_IN_SECONDS = 60

fun main() {
    val input = 13600
    val h = input / ONE_HOUR_IN_SECONDS
    val min = (input - (h * ONE_HOUR_IN_SECONDS)) / ONE_MINUTE_IN_SECONDS
    val s = input - (h * ONE_HOUR_IN_SECONDS) - (min * ONE_MINUTE_IN_SECONDS)
    println("${h}h${min}min${s}s")
}
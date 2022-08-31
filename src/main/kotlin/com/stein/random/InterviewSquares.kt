package com.stein.random

fun main() {
    a()
    c()
}



fun c() {
    val m = listOf(
        listOf("", "", "", ""),
        listOf("", "", "", ""),
        listOf("", "", "", ""),
        listOf("", "", "", ""),
    )

    var count = 0

    for (dimension in 1 until m.size)
        for (i in 0 until (m.size - dimension))
            for (j in 0 until (m.size - dimension))
                count++


    println(count)
}

// x x x
// x x x
// x x x

fun a() {

    val y = 3
    var total = 0;

    for (i in 1..y) {
        total += i * i
    }

    println(total)
}

fun b(x: Int, y: Int): Int {
    val maxOf = maxOf(x, y)
    val minOf = minOf(x, y)
    val diff = maxOf - minOf

    var total = 0

    for (i in 1..minOf) {
        total += i * i
    }

    if (diff > 0)
        total += b(diff + 1, minOf)

    println(total)

    return total
}
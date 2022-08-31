package com.stein.hackerrank

import kotlin.math.ceil
import kotlin.math.sqrt

private fun encryption(s: String): String {
    val string = s.replace(" ", "")

    val base = sqrt(string.length.toDouble())
    println("base = ${base}")

    val colLen = ceil(base).toInt()
    println("colLen = ${colLen}")

    val result = StringBuilder()
    for (x in 0 until colLen) {
        for (y in x..string.lastIndex step colLen) {
            result.append(string[y])
        }
        if (x < colLen - 1) {
            result.append(" ")
        }
    }

    println("string = ${string}")
    println("result2 = [$result]")

    return result.toString()
}

fun main() {
    encryption("haveaniceday")
    encryption("feedthedog")
    encryption("chillout")
}
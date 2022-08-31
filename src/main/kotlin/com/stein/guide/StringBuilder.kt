package com.stein

import java.lang.StringBuilder
import kotlin.random.Random

fun main() {
    val s = StringBuilder()
    repeat(Random.nextInt(0, 10)){
        s.append("word ")
    }
    print(s.toString())
}
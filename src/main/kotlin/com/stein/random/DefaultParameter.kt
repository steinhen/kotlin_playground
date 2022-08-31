package com.stein.random

fun main() {
    doIt(Something("b"), another = "c")
}

fun doIt(something: Something, a: String = something.a, another: String) {
    println(a)
}

data class Something (val a: String)
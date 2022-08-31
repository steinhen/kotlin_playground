package com.stein.random

class A {
    fun doSomething() {
        println("Doing Something")
    }
}

object B {
    fun A.doExtra() {
        doSomething()
        println("Extra")
    }
}

fun main() {
    val a = A()
    with(B) {
        a.doExtra()
    }
}
package com.stein.guide

private fun simple(): Sequence<Int> = sequence { // sequence builder
    for (i in 1..3) {
        Thread.sleep(1000) // pretend we are computing it
        yield(i) // yield next value
    }
}

fun main() {
    val n = 5
    println(primes.take(n).toList())
    println("-------------")
    println(primes2.take(n).toList())
//    simple().forEach { value -> println(value) }
}

val primes: Sequence<Int> = sequence {
    var numbers = generateSequence(2) { it + 1 }

    while (true) {
        val prime = numbers.first()
        yield(prime)
        numbers = numbers.drop(1)
            .filter {
                println("filter $it % $prime")
                it % prime != 0
            }
    }
}

val primes2: Sequence<Int> = sequence {
    var numbers = generateSequence(2) { it + 1 }
    var prime: Int
    while (true) {
        prime = numbers.first()
        yield(prime)
        numbers = numbers.drop(1)
            .filter {
                println("filter $it % $prime")
                it % prime != 0
            }
    }
}
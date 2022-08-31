package com.stein.guide

fun main() {
    Car() drive 10
}

class Car {
    infix fun drive(distance: Int) {
        println("Driving $distance")
    }
}
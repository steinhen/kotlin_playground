package com.stein.designpatterns

fun main() {
    ObjSingleton.printAndIncrement()
    ObjSingleton.printAndIncrement()
    ObjSingleton.printAndIncrement()

    Singleton.INSTANCE.printAndIncrement()
    Singleton.INSTANCE.printAndIncrement()
    Singleton.INSTANCE.printAndIncrement()
}

enum class Singleton {
    INSTANCE;
    init {
        println("Init Singleton")
    }
    private var count: Int = 0
    fun printAndIncrement() = println(count++)
}

object ObjSingleton {
    private var count: Int = 0
    init {
        println("Init ObjSingleton")
    }
    fun printAndIncrement() = println(count++)
}
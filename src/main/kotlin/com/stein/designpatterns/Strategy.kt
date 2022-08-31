package com.stein.designpatterns

fun main() {
    DeadDuck().trigger()
    MallardDuck().trigger()
    RubberDuck().trigger()
}

fun Duck.trigger() {
    println("${this.javaClass}:")
    swim()
    fly()
    quack()
    println()
}

class MallardDuck : Duck(FlyWithWings(), Quack())

class RubberDuck : Duck(FlyNoWay(), Squeak())

class DeadDuck : Duck() {
    override fun swim() {
        println("I cannot swim because Im dead")
    }
}

abstract class Duck(
    private val flyBehavior: FlyBehavior = FlyNoWay(),
    private val quackBehavior: QuackBehavior = MuteQuack()
) {
    fun quack() {
        quackBehavior.quack()
    }

    fun fly() {
        flyBehavior.fly()
    }

    open fun swim() {
        println("Im swimming")
    }
}



interface FlyBehavior {
    fun fly()
}

class FlyWithWings : FlyBehavior {
    override fun fly() {
        println("Im flying")
    }
}

class FlyNoWay : FlyBehavior {
    override fun fly() {
        println("I cannot fly")
    }
}

interface QuackBehavior {
    fun quack()
}

class Quack : QuackBehavior {
    override fun quack() {
        println("Quack!")
    }
}

class Squeak : QuackBehavior {
    override fun quack() {
        println("Squeak!")
    }
}

class MuteQuack : QuackBehavior {
    override fun quack() {
        println("!")
    }
}
package com.stein.designpatterns

fun main() {
    println(Mocha(Mocha(Americano())).toString())
    println(Mocha(Expresso()).toString())
}

abstract class Beverage {
    abstract fun getDescription(): String
    abstract fun getCost(): Float
    override fun toString() = "${this.getDescription()}: ${this.getCost()} Euros"
}

class Americano : Beverage() {
    override fun getDescription() = "Americano"
    override fun getCost() = 1.99F
}

class Expresso : Beverage() {
    override fun getDescription() = "Expresso"
    override fun getCost() = 0.99F
}

abstract class BeverageDecorator(protected val beverage: Beverage) : Beverage()

class Mocha(beverage: Beverage) : BeverageDecorator(beverage) {
    override fun getDescription() = beverage.getDescription() + " Mocha"
    override fun getCost() = beverage.getCost() + 0.15F
}
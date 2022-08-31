package com.stein.guide

fun main() {
    ParentClass(MyClass()).anything()
}

class ParentClass(private val myClass: MyClass) : MyInterface by myClass {
    fun anything() {
        myClass.another()
    }
}

interface MyInterface {
    fun doSomething()
    fun another()
}

class MyClass : MyInterface {
    override fun doSomething() {
        print("print")
    }

    override fun another() {
        print("another")
    }
}
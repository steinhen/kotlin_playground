package com.stein.designpatterns

fun main() {
    val data = WeatherData()
    data.register(CurrentCondition())
    data.register(AnotherDisplay())

    data.humidity = 20
    data.temperature = -16
    data.pressure = 900
}

class WeatherData : Subject {
    var temperature: Int = 0
        set(value) {
            field = value
            notify(Data(temperature, humidity, pressure))
        }
    var humidity: Int = 0
        set(value) {
            field = value
            notify(Data(temperature, humidity, pressure))
        }
    var pressure: Int = 0
        set(value) {
            field = value
            notify(Data(temperature, humidity, pressure))
        }

    private val observers = mutableListOf<Observer>()

    override fun register(observer: Observer) {
        observers.add(observer)
    }

    override fun unregister(observer: Observer) {
        observers.remove(observer)
    }

    override fun notify(data: Data) {
        observers.map {
            it.update(Data(temperature, humidity, pressure))
        }
    }
}

class CurrentCondition : Observer {
    override fun update(data: Data) {
        print("${this.javaClass}:")
        println(data)
    }
}

class AnotherDisplay : Observer {
    override fun update(data: Data) {
        print("${this.javaClass}:")
        println(data)
    }
}

interface Observer {
    fun update(data: Data)
}

data class Data(
    val temperature: Int,
    val humidity: Int,
    val pressure: Int
)

interface Subject {
    fun register(observer: Observer)
    fun unregister(observer: Observer)
    fun notify(data: Data)
}
package com.stein.random

import kotlin.math.max
import kotlin.math.pow

val offers = intArrayOf(1, 10, 10, 2, 1)
val sales = intArrayOf(3, 6, 5, 4, 3)
const val capital = 11

fun main() {
    printMaximumProfit() // prints 6
}

fun printMaximumProfit() {}

fun maximumProfit(capital: Int): Int {
    val numSelections = 2.0.pow(offers.size.toDouble()).toInt()
    var maxProfit = 0
    for (i in 0 until numSelections) {
        var profit = 0
        var costs = 0
        for (j in offers.indices) {
            if (i shr j and 1 > 0) {
                costs += offers[j]
                profit += sales[j] - offers[j]
            }
        }
        if (costs <= capital && profit > maxProfit) {
            maxProfit = profit
        }
    }
    return maxProfit
}

fun maximumProfit(capital: Int, index: Int): Int { // index is zero on initial call
    if (index == offers.size) {
        return 0
    }
    return if (offers[index] > capital) {
        // skip offer when has no money
        maximumProfit(capital, index + 1)
    } else {
        // take offer profit
        val profit = sales[index] - offers[index]
        val adjustedCapital = capital - offers[index]

        val currentProfitPlusNext = profit + maximumProfit(adjustedCapital, index + 1)
        val ignoreCurrentProfit = maximumProfit(capital, index + 1)

        max(currentProfitPlusNext, ignoreCurrentProfit)
    }
}
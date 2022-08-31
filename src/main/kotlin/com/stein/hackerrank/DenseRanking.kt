package com.stein.hackerrank

fun climbingLeaderboard(ranked: Array<Int>, player: Array<Int>): Array<Int> {
    val result = mutableListOf<Int>()

    var position = 1
    var previous = ranked.first()
    var j = 0
    var i = player.lastIndex

    while (i >= 0) {

        val score = player[i]

        // takes current from ranked
        var current = ranked[j]

        if (score >= current) {
            result.add(0, position)
            i--
            continue
        }

        while (score < current && j < ranked.size - 1) {
            current = ranked[j + 1]
            // adjust position
            if (current != previous) {
                position++
            }
            previous = ranked[j + 1]
            j++
        }

        if (score < ranked.last()) {
            result.add(0, position + 1)
            i--
        }

    }

    return result.toTypedArray()

}

fun main() {
    println(
        climbingLeaderboard(
            intArrayOf(100, 100, 50, 40, 20, 10).toTypedArray(),
            intArrayOf(5, 25, 40, 50, 120).toTypedArray()
        ).toList()
    )

    println(
        climbingLeaderboard(
            intArrayOf(100, 90, 90, 80, 75, 60).toTypedArray(),
            intArrayOf(102, 102, 102, 102, 102, 102, 102).toTypedArray()
        ).toList()
    )
}
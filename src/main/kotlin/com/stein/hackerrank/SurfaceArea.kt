package com.stein.hackerrank

fun surfaceArea(A: Array<Array<Int>>): Int {
    // Write your code here
    /**
     * 1+3+4=8
     * 4+3+4=11
     * 1+2+4=7
     * 1+2+1=4
     * 30
     * 9+9=18
     * 48
     * 2+1=3
     * 0+1=1
     * 1+2=3
     * 1+1=2
     * 1+0=1
     * 1+1=2
     * 12
     */
    val h = A.lastIndex
    val w = A[0].lastIndex

    var side = 0
    var top = 0
    var diff = 0

    for (y in 0..h) {
        for (x in 0..w) {
            if (y == 0) side += A[y][x]
            if (y == h) side += A[y][x]
            if (x == 0) side += A[y][x]
            if (x == w) side += A[y][x]
            if (A[y][x] > 0) top++
            if (y + 1 <= h) diff += Math.abs(A[y][x] - A[y + 1][x])
            if (x + 1 <= w) diff += Math.abs(A[y][x] - A[y][x + 1])
        }
    }

    return side + (2 * top) + diff
}


fun main() {
    println(
        surfaceArea(
            arrayOf(
                arrayOf(1, 1, 1),
                arrayOf(1, 0, 1),
                arrayOf(1, 1, 1)
            )
        )
    )
    println(
        surfaceArea(
            arrayOf(
                arrayOf(1)
            )
        )
    )
    println(
        surfaceArea(
            arrayOf(
                arrayOf(1, 3, 4),
                arrayOf(2, 2, 3),
                arrayOf(1, 2, 4)
            )
        )
    )
}
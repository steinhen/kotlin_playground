package com.stein.hackerrank

fun queensAttack(n: Int, k: Int, r_q: Int, c_q: Int, obstacles: Array<Array<Int>>): Int {
    // Write your code here
    val obstaclesSet = obstacles.map { Pair(it[0], it[1]) }.toSet()

    val top = DirectionProcessor(n, r_q, c_q, obstaclesSet, { r, step -> r + step }, { c, _ -> c })
    val topRight = DirectionProcessor(n, r_q, c_q, obstaclesSet, { r, step -> r + step }, { c, step -> c + step })
    val right = DirectionProcessor(n, r_q, c_q, obstaclesSet, { r, _ -> r }, { c, step -> c + step })
    val bottomRight = DirectionProcessor(n, r_q, c_q, obstaclesSet, { r, step -> r - step }, { c, step -> c + step })
    val bottom = DirectionProcessor(n, r_q, c_q, obstaclesSet, { r, step -> r - step }, { c, _ -> c })
    val bottomLeft = DirectionProcessor(n, r_q, c_q, obstaclesSet, { r, step -> r - step }, { c, step -> c - step })
    val left = DirectionProcessor(n, r_q, c_q, obstaclesSet, { r, _ -> r }, { c, step -> c - step })
    val topLeft = DirectionProcessor(n, r_q, c_q, obstaclesSet, { r, step -> r + step }, { c, step -> c - step })

    var attackablePositionCount = 0

    for (i in 1 until n) {
        if (top.canAttack(i)) attackablePositionCount++
        if (topRight.canAttack(i)) attackablePositionCount++
        if (right.canAttack(i)) attackablePositionCount++
        if (bottomRight.canAttack(i)) attackablePositionCount++
        if (bottom.canAttack(i)) attackablePositionCount++
        if (bottomLeft.canAttack(i)) attackablePositionCount++
        if (left.canAttack(i)) attackablePositionCount++
        if (topLeft.canAttack(i)) attackablePositionCount++
    }

    return attackablePositionCount
}

class DirectionProcessor(
    private val n: Int,
    private val qr: Int,
    private val qc: Int,
    private val obstacles: Set<Pair<Int, Int>>,
    private val rowFunction: (r: Int, step: Int) -> Int,
    private val colFunction: (c: Int, step: Int) -> Int,
) {
    private var valid = true
    fun canAttack(step: Int): Boolean {
        if (!valid) return false
        val row = rowFunction(qr, step)
        val col = colFunction(qc, step)
        if (row.isOutOfBoundaries()
            || col.isOutOfBoundaries()
            || this.obstacles.contains(Pair(row, col))
        ) {
            valid = false
            return false
        }
        return true
    }

    private fun Int.isOutOfBoundaries(): Boolean {
        return this < 1 || this > n
    }
}

fun main() {
    println(queensAttack(4, 0, 4, 4, emptyArray()))
    println(queensAttack(5, 3, 4, 3, arrayOf(arrayOf(5, 5), arrayOf(4, 2), arrayOf(2, 3))))
    println(queensAttack(1, 0, 1, 1, emptyArray()))
}
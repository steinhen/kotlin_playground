package com.stein.hackerrank

/**
 * 123
 * 132
 * 213
 * 231
 * 312
 * 321
 */

fun main() {
    val list = mutableListOf(1, 2, 3)
    generate(list, 0)
}

fun generate(list: MutableList<Int>, start: Int) {
    val listCopy = list.toMutableList()
    if (start == list.lastIndex)
        println(list)
    else {
        for (i in start .. list.lastIndex) {
            swap(listCopy, start, i)
            generate(listCopy, start + 1)
        }
    }
}

fun swap(list: MutableList<Int>, a: Int, b: Int) {
    val temp = list[a]
    list[a] = list[b]
    list[b] = temp
}
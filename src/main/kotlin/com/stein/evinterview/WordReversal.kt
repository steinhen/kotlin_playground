package com.stein.evinterview

fun main() {
    val phrase = "today is monday".toCharArray()
    var endOfWord = phrase.size
    var result = ""
    for (i in phrase.size - 1 downTo 1) {
        if (phrase[i - 1] == ' ') {
            for (j in i until endOfWord) {
                result += phrase[j]
            }
            result += ' '
            endOfWord = i - 1
        }
    }
    for (j in 0 until endOfWord) {
        result += phrase[j]
    }
    println(result)
}
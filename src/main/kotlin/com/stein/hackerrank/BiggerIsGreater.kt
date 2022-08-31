package com.stein.hackerrank

fun biggerIsGreater(w: String): String {
    val word = w.toCharArray()
    for (i in word.lastIndex downTo 1) {
        if (word[i - 1] < word[i]) {
            for (j in word.lastIndex downTo i) {
                if (word[j] > word[i - 1]) {
                    val temp = word[i - 1]
                    word[i - 1] = word[j]
                    word[j] = temp
                    break
                }
            }
            var j = w.lastIndex
            var k = i
            while (k < j) {
                val temp = word[k]
                word[k] = word[j]
                word[j] = temp
                j--
                k++
            }
            break
        }
    }

    val sb = StringBuilder()
    word.forEach { sb.append(it) }
    val result = sb.toString()

    return if (result == w) "no answer" else result
}


fun main() {
    listOf("dkhc", "lmno", "dcba", "22125432", "bb", "ab").forEach {
        println(biggerIsGreater(it))
    }
}
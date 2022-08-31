package com.stein.hackerrank


fun timeInWords(h: Int, m: Int) {
    println(calculate(h, m))
}

fun calculate(h: Int, m: Int): String {
    return if (m == 0) "${hourToWord(h)} o' clock"
    else {
        val hour = if (m <= 30) {
            "past " + hourToWord(h)
        } else {
            "to " + hourToWord(h + 1)
        }
        "${minToWord(m)} $hour"
    }
}

fun hourToWord(i: Int): String {
    return toWords(i)
}

fun minToWord(i: Int): String {
    return if (i % 15 == 0) {
        toWords(i)
    } else {
        val adjusted = if (i > 30) 60 - i else i
        toWords(adjusted) + " minute${if (adjusted == 1) "" else "s"}"
    }
}

private fun toWords(i: Int): String {
    return when (i) {
        1 -> "one"
        2 -> "two"
        3 -> "three"
        4 -> "four"
        5 -> "five"
        6 -> "six"
        7 -> "seven"
        8 -> "eight"
        9 -> "nine"
        10 -> "ten"
        11 -> "eleven"
        12 -> "twelve"
        13 -> "thirteen"
        15 -> "quarter"
        20 -> "twenty"
        30 -> "half"
        45 -> "quarter"
        else -> compoundToWord(i)
    }
}

fun compoundToWord(i: Int): String {
    return when {
        i <= 13 -> toWords(i)
        i <= 19 -> toWords(i % 10) + "teen"
        i <= 29 -> toWords(20) + " " + toWords(i % 20)
        else -> ""
    }
}


fun main() {
    timeInWords(4, 0)
    timeInWords(4, 1)
    timeInWords(4, 2)
    timeInWords(4, 12)
    timeInWords(4, 13)
    timeInWords(4, 14)
    timeInWords(4, 15)
    timeInWords(4, 21)
    timeInWords(4, 26)
    timeInWords(4, 30)
    timeInWords(4, 45)
    timeInWords(4, 59)
}
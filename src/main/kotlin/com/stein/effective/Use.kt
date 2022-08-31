package com.stein.effective

import java.io.File

fun countCharactersInFile(filepath: String): Int =
    File(filepath).useLines { lines ->
        lines.sumOf { it.length }
    }

fun main() {
    println(
        countCharactersInFile("/Users/henriquestein/stein/kotlin/kotlin/src/main/resources/test.txt")
    )
}
package com.stein.leetcode


fun lengthOfLongestSubstring(s: String): Int {
    val list = mutableListOf<Char>()
    var longest = 0

    if (s.isEmpty()) return 0

    for (i in s.indices) {
        val element = s[i]

        for (j in 0..list.indexOf(element)) {
            list.removeAt(0)
        }

        list.add(element)

        if (list.size > longest) longest = list.size
    }

    return longest
}

fun main() {
    println(lengthOfLongestSubstring("aaa"))
    println(lengthOfLongestSubstring("abcabcbb"))
    println(lengthOfLongestSubstring("pwwkew"))
    println(lengthOfLongestSubstring("dvdf"))
}
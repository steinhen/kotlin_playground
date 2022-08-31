package com.stein.leetcode

object Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        for (i in nums.indices)
            for (j in i until nums.size)
                if (nums[i] + nums[j] == target) return intArrayOf(i, j)
        throw RuntimeException("Exception")
    }

    fun twoSum2(nums: IntArray, target: Int): IntArray {
        val map = nums.mapIndexed { i, num -> num to i }.toMap()
        nums.mapIndexed { i, num ->
            val complement = target - num
            if (map.containsKey(complement) && map[complement] != i)
                return intArrayOf(i, map.getValue(complement))
        }
        throw RuntimeException("Exception")
    }

    fun twoSum3(nums: IntArray, target: Int): IntArray {
        val map = mutableMapOf<Int, Int>()
        nums.mapIndexed { i, curr ->
            val complement = target - curr
            if (map.containsKey(complement))
                return intArrayOf(map.getValue(complement), i)
            map[curr] = i
        }
        throw RuntimeException("Exception")
    }
}

fun main() {
    Solution.twoSum2(intArrayOf(3, 3), 6)
        .forEach { println(it) }
}
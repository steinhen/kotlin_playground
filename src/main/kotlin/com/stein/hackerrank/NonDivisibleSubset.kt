
import kotlin.math.max
import kotlin.math.min

fun main() {
    val map = mutableMapOf<Int, Int>()

    val k = 6
    listOf(1, 7, 2, 4)
//    listOf(1, 2, 3, 9, 5, 6)
    listOf(1, 7, 2, 3, 4, 5, 6)
    listOf(12, 6, 1, 9, 13, 15, 10, 21, 14, 32, 5, 8, 23, 19)
        .forEach {
            val mod = it % k
            println("$it % k= $mod")
            map[mod] = map.computeIfAbsent(mod) { 0 } + 1
        }

    var count = min(map[0] ?: 0, 1) // start counting at max 1 which is divisible

    (1..k / 2).forEach { i ->
        if (i != k - i)
            count += max(map[i] ?: 0, map[k - i] ?: 0)
    }

    if (k % 2 == 0) count += min(map[k / 2] ?: 0, 1)

    println(map)
    println(count)

}
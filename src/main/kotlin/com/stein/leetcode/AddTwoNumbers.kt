package com.stein.leetcode

data class ListNode(var value: Int,
                    var next: ListNode? = null) {

}

fun main() {
    println(
        addTwoNumbers(
            ListNode(9).also {
                it.next = ListNode(9).also {
                    it.next = ListNode(9).also {
                        it.next = ListNode(9).also {
                            it.next = ListNode(9).also {
                                it.next = ListNode(9).also {
                                    it.next = ListNode(9)
                                }
                            }
                        }
                    }
                }
            },
            ListNode(9).also {
                it.next = ListNode(9).also {
                    it.next = ListNode(9).also {
                        it.next = ListNode(9)
                    }
                }
            }
        )
    )
}

// 2 -> 4 -> 3 = 342
// 5 -> 6 -> 4 = 465
// 807

//9 -> 9
//9
fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    if (l1 == null && l2 == null) return null
    val a = l1?.value ?: 0
    val b = l2?.value ?: 0

    (a + b).let { sum ->
        return if (sum >= 10) {
            ListNode(sum % 10).also {
                if (l1?.next != null) {
                    l1.next!!.value++
                } else {
                    l1?.next = ListNode(1)
                }
                it.next = addTwoNumbers(l1?.next, l2?.next)
            }
        } else {
            ListNode(sum).also {
                it.next = addTwoNumbers(l1?.next, l2?.next)
            }
        }
    }
}

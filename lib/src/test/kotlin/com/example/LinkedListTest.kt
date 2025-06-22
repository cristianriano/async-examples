package com.example

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LinkedListTest {

  @Test
  fun `reverse LinkedList`() {
    val nodeE = ListNode(5)
    val nodeD = ListNode(4)
    nodeD.next = nodeE
    val nodeC = ListNode(3)
    nodeC.next = nodeD
    val nodeB = ListNode(2)
    nodeB.next = nodeC
    val nodeA = ListNode(1)
    nodeA.next = nodeB

    var res = LinkedList().reverseList(nodeA)
    var expectedValue = 5

    while (expectedValue > 0) {
      assertThat(res?.value).isEqualTo(expectedValue)
      res = res?.next
      expectedValue--
    }
  }
}
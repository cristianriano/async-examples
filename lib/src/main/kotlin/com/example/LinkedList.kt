package com.example

class LinkedList {
  fun reverseList(head: ListNode?): ListNode? {
    var prev: ListNode? = null
    var current = head
    var next = current?.next

    while(current != null) {
      current.next = prev
      prev = current
      current = next
      next = next?.next
    }

    return prev
  }

}

class ListNode(var value: Int) {
  var next: ListNode? = null
}

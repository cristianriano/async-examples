package com.example

import java.util.PriorityQueue

interface SortedStack<T : Comparable<T>> {
  fun peek(): T
  fun pop(): T
  fun push(element: T)
  fun isEmpty(): Boolean
  fun size(): Int
}

interface Heap {
  fun add(element: Int): Unit
  fun poll(): Int
  fun peek(): Int
  fun isEmpty(): Boolean
}

class IncreasingStack<T : Comparable<T>> : SortedStack<T> {
  private val tmp = ArrayDeque<T>()
  private val actual = ArrayDeque<T>()

  override fun peek(): T = actual.first()

  override fun pop(): T = actual.removeFirst()

  override fun push(element: T) {
    if (actual.isEmpty()) return actual.addFirst(element)

    while (!actual.isEmpty() && element > actual.first()) {
      tmp.addFirst(actual.removeFirst())
    }

    actual.addFirst(element)

    while (!tmp.isEmpty()) {
      actual.addFirst(tmp.removeFirst())
    }
  }

  override fun isEmpty() = actual.isEmpty()

  override fun size() = actual.size
}

class MinHeap(val capacity: Int): Heap {
  private val queue = PriorityQueue<Int>(capacity)

  override fun add(element: Int) {
    queue.add(element)
  }

  override fun poll(): Int {
    if (isEmpty()) throw IllegalArgumentException()

    return queue.poll()!!
  }

  override fun peek(): Int {
    if (isEmpty()) throw IllegalArgumentException()

    return queue.peek()!!
  }

  override fun isEmpty() = queue.isEmpty()
}

class MaxHeap(val capacity: Int) : Heap {
  private var size = 0
  private var items = IntArray(capacity)

  override fun add(element: Int) {
    items[size] = element
    size++
    heapifyUp()
  }

  override fun poll(): Int {
    if (isEmpty()) throw IllegalArgumentException()

    val element = items[0]
    items[0] = items[size - 1]
    size--
    heapifyDown()

    return element
  }

  override fun peek(): Int {
    if (isEmpty()) throw IllegalArgumentException()

    return items[0]
  }

  fun parentOf(index: Int) = items[parentIndexOf(index)]

  fun leftChildOf(index: Int) = items[leftChildIndexOf(index)]
  fun rightChildOf(index: Int) = items[rightChildIndexOf(index)]
  private fun parentIndexOf(index: Int) = (index - 1) / 2

  private fun leftChildIndexOf(index: Int) = (index * 2) + 1
  private fun rightChildIndexOf(index: Int) = (index * 2) + 2

  private fun heapifyUp() {
    var cursor = size - 1

    while (items[cursor] > parentOf(cursor)) {
      swap(cursor, parentIndexOf(cursor))
      cursor = parentIndexOf(cursor)
    }
  }

  private fun heapifyDown() {
    var cursor = 0

    while (items[cursor] < leftChildOf(cursor)) {
      if (leftChildOf(cursor) > rightChildOf(cursor)) {
        swap(leftChildIndexOf(cursor), cursor)
        cursor = leftChildIndexOf(cursor)
      } else {
        swap(rightChildIndexOf(cursor), cursor)
        cursor = rightChildIndexOf(cursor)
      }
    }
  }

  private fun swap(x: Int, y: Int) {
    val tmp = items[x]
    items[x] = items[y]
    items[y] = tmp
  }

  override fun isEmpty() = size == 0
}
package com.example

interface SortedStack<T : Comparable<T>> {
  fun peek(): T
  fun pop(): T
  fun push(element: T)
  fun isEmpty(): Boolean
  fun size(): Int
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
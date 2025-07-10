package com.example

interface Cache<T> {
  fun put(key: String, value: T)
  fun get(key: String): T?
}

class LRUCache<T>(val capacity: Int): Cache<T> {
  private var cache = mutableMapOf<String, Node<T>>()
  private var head: Node<T> = Node(null, null)
  private var tail: Node<T> = Node(null, null)

  init {
    head.next = tail
    tail.prev = head
  }

  override fun put(key: String, value: T) {
    cache.put(key, Node(key, value, null, null))
  }

  override fun get(key: String): T? {
    return cache.get(key)?.value
  }

  private data class Node<T>(val key: String?, var value: T?, var next: Node<T>?, var prev: Node<T>?) {
    constructor(key: String?, value: T?): this(key, value, null, null)
  }
}

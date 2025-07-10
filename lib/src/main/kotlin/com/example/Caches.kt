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
    if (capacity <= 0) throw IllegalArgumentException("Capacity must be at least 1")
    head.next = tail
    tail.prev = head
  }

  override fun put(key: String, value: T) {
    if (capacity == cache.size) {
      // Remove older
      deleteNode(tail.prev!!.key!!)
    }

    if (cache.contains(key)) deleteNode(key)
    val node = Node(key, value, head.next, head)
    moveToFront(node)
    cache.put(key, node)
  }

  override fun get(key: String): T? {
    val node = cache[key]

    if (node != null) {
      deleteNode(key)
      moveToFront(node)
    }
    return node?.value
  }

  private fun deleteNode(key: String) {
    val node = cache[key]!!
    node.prev!!.next = node.next
    node.next!!.prev = node.prev
    cache.remove(key)
  }

  private fun moveToFront(node: Node<T>) {
    val second = head.next!!
    head.next = node
    node.prev = head
    second.prev = node
    node.next = second
  }

  private data class Node<T>(val key: String?, var value: T?, var next: Node<T>?, var prev: Node<T>?) {
    constructor(key: String?, value: T?): this(key, value, null, null)
  }
}

package com.example

interface Cache<K, T> {
  fun put(key: K, value: T)
  fun get(key: K): T?
}

class LRUCache<K, T>(val capacity: Int): Cache<K, T> {
  private var cache = mutableMapOf<K, Node<K, T>>()
  private var head: Node<K, T> = Node(null, null)
  private var tail: Node<K, T> = Node(null, null)

  init {
    if (capacity <= 0) throw IllegalArgumentException("Capacity must be at least 1")
    head.next = tail
    tail.prev = head
  }

  override fun put(key: K, value: T) {
    if (capacity == cache.size) {
      // Remove older
      deleteNode(tail.prev!!.key!!)
    }

    if (cache.contains(key)) deleteNode(key)

    val node = Node(key, value)
    node.prev = head
    node.next = head.next
    moveToFront(node)
    cache.put(key, node)
  }

  override fun get(key: K): T? {
    val node = cache[key]

    if (node != null) {
      deleteNode(key)
      moveToFront(node)
    }
    return node?.value
  }

  private fun deleteNode(key: K) {
    val node = cache[key]!!
    node.prev!!.next = node.next
    node.next!!.prev = node.prev
    cache.remove(key)
  }

  private fun moveToFront(node: Node<K, T>) {
    val second = head.next!!
    head.next = node
    node.prev = head
    second.prev = node
    node.next = second
  }

  private data class Node<K, T>(val key: K?, var value: T?) {
    var prev: Node<K, T>? = null
    var next: Node<K, T>? = null
  }
}

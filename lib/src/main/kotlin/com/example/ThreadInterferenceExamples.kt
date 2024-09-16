package com.example

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

interface Counter {
  fun increment()

  fun decrement()

  fun count(): Int
}

class ThreadInterferenceExamples {

  fun runUnSync(): Int {
    val c = SimpleCounter()

    val t1 = Thread(Incrementer(c))
    val t2 = Thread(Decrementer(c))

    t1.start()
    t2.start()

    t1.join() // Waiting
    t2.join()

    return c.count()
  }

  fun runSync(): Int {
    val c = SynchronizedCounter()

    val t1 = Thread(Incrementer(c))
    val t2 = Thread(Decrementer(c))

    t1.start()
    t2.start()

    t1.join() // Waiting
    t2.join()

    return c.count()
  }
}

internal class SimpleCounter : Counter {
  private var c = 0

  override fun increment() {
    c++
  }

  override fun decrement() {
    c--
  }

  override fun count() = c
}

@OptIn(InternalCoroutinesApi::class)
internal class SynchronizedCounter : Counter {

  private var c = 0

  override fun increment() {
    synchronized(this) {
      c++
    } // Acquire the lock for Counter obj. In Java, keyword can also be at method level
  }

  override fun decrement() {
    synchronized(this) { c-- }
  }

  override fun count(): Int = synchronized(this) { c }
}

internal class Incrementer(private val counter: Counter) : Runnable {
  override fun run() {
    //    println("Starting Incrementer")
    Thread.sleep(100)

    repeat(100) { counter.increment() }
  }
}

internal class Decrementer(private val counter: Counter) : Runnable {
  override fun run() {
    //    println("Starting Decrementer")
    Thread.sleep(100)

    repeat(100) { counter.decrement() }
  }
}

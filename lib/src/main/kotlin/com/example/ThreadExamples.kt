package com.example

internal class Counter {
  private var c = 0;

  fun increment() {
    c++
  }

  fun decrement() {
    c--
  }

  fun count() = c
}

internal class Incrementer(private val counter: Counter): Runnable {
  override fun run() {
//    println("Starting Incrementer")
    Thread.sleep(100)

    repeat(1000) {
      counter.increment()
    }
  }
}

internal class Decrementer(private val counter: Counter): Runnable {
  override fun run() {
//    println("Starting Decrementer")
    Thread.sleep(100)

    repeat(1000) {
      counter.decrement()
    }
  }
}

class ThreadExamples {

  fun runUnSync(): Int {
    val c = Counter()

    val t1 = Thread(Incrementer(c))
    val t2 = Thread(Decrementer(c))

    t1.start()
    t2.start()

//    println("Waiting...")
    t1.join()
    t2.join()

    return c.count()
  }
}
package com.example

import kotlinx.coroutines.*
import java.lang.RuntimeException

class CoroutinesExamples {

  fun usingRunBlocking(): Unit {
    runBlocking {
      println("1")

      launch {
        delay(100)
        println("4")
      }

      println("2")
    }
  }

  fun runningInParallel() {
    runBlocking {
      val defer1: Deferred<Long> = async { printN(1) }
      val defer2: Deferred<Long> = async { printN(2) }
      val defer3: Deferred<Long> = async { printN(3) }

      println(
        defer1.await() + defer2.await() + defer3.await()
      )
    }
  }

  fun cancelAfterOneFailure(): Result<String> {
    val res: Result<String> = runCatching {
      runBlocking {

        launch { printN(1) }
        launch { printN(2) }
        launch { waitAndFail() }

        "Success"
      }
    }

    Thread.sleep(2_000)
    return res
  }

  private suspend fun printN(n: Long): Long {
    delay(2_000)
    println(n)
    return n
  }

  private suspend fun waitAndFail() {
    delay(1000)
    throw RuntimeException("Boom")
  }
}
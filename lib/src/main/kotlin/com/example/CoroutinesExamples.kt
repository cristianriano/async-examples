package com.example

import java.lang.RuntimeException
import java.util.concurrent.ExecutorService
import kotlinx.coroutines.*

class CoroutinesExamples(private val executor: ExecutorService) {

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

      println(defer1.await() + defer2.await() + defer3.await())
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

    Thread.sleep(1_200)
    return res
  }

  fun fireAndForget() {
    executor.submit { runBlocking { printN(1) } }
    println("Fired!")
  }

  private suspend fun printN(n: Long): Long {
    delay(1_000)
    println(n)
    return n
  }

  private suspend fun waitAndFail() {
    delay(500)
    throw RuntimeException("Boom")
  }
}

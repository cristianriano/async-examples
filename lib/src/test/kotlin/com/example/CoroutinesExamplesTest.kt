package com.example

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.concurrent.Executors

class CoroutinesExamplesTest {

  private val executor = Executors.newSingleThreadExecutor()
  private val examples = CoroutinesExamples(executor)

  @Test
  fun `when using runBlocking prints in correct order`() {
    examples.usingRunBlocking()
  }

  @Test
  fun `when running in parallel it waits once`() {
    examples.runningInParallel()
  }

  @Test
  fun `it cancels other coroutines`() {
    val res = examples.cancelAfterOneFailure()

    assertThat(res.exceptionOrNull()).isNotNull()
  }

  @Test
  fun `it fires and forgets`() {
    examples.fireAndForget()
    println("Done!")
    Thread.sleep(3_000)
  }
}

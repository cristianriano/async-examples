package com.example

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CoroutinesExamplesTest {

  private val examples = CoroutinesExamples()

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


}
package com.example

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ThreadExamplesTest {

  private val examples = ThreadExamples()

  @Test
  fun `increment and decrement concurrently without sync`() {
    val sum = (1..5).map { examples.runUnSync() }.sum()

    assertThat(sum).isNotZero()
  }
}
package com.example

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class ThreadInterferenceExamplesTest {

  private val examples = ThreadInterferenceExamples()

  @Test
  @Disabled("In CI can't trigger the race condition")
  fun `when concurrent and without sync it sums different than 0`() {
    val sum = (1..5).sumOf { examples.runUnSync() }

    assertThat(sum).isNotZero()
  }

  @Test
  fun `when sync it sums 0`() {
    val sum = (1..5).sumOf { examples.runSync() }

    assertThat(sum).isZero()
  }
}

package com.example

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.streams.asStream

class SolutionTest {

  val solution = Solution()

  @ParameterizedTest
  @MethodSource("params")
  fun `when sequence is repeated`(sequence: String, word: String, maxRepetitions: Int) {
    assertThat(solution.maxRepeating(sequence, word)).isEqualTo(maxRepetitions)
  }

  companion object {

    @JvmStatic
    fun params() = sequenceOf(
      Arguments.of("ababc", "ab", 2)
    ).asStream()
  }
}
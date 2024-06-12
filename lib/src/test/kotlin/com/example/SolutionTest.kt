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
  fun `containsPattern`(arr: IntArray, m: Int, k: Int, contains: Boolean) {
    assertThat(solution.containsPattern(arr, m, k)).isEqualTo(contains)
  }

  companion object {

    @JvmStatic
    fun params() = sequenceOf(
      Arguments.of(intArrayOf(1,2,4,4,4,4), 1, 3, true),
      Arguments.of(intArrayOf(1,2,1,2,1,3), 2, 3, false),
      Arguments.of(intArrayOf(99,9), 1, 3, false),
      Arguments.of(intArrayOf(1,2,1,2,4,1,2), 2, 3, false),
    ).asStream()
  }
}
package com.example

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SolutionTest {

  @Test
  fun `zigzag list`() {
    val x = Solution()
    assertThat(x.solution(mutableListOf(1,2,1,3,4))).isEqualTo(mutableListOf(1,1,0))
  }
}
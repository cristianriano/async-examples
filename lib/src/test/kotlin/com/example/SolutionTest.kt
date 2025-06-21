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

  @Test
  fun `two sum`() {
    val x = Solution()
    assertThat(x.twoSum(intArrayOf(2, 7, 11, 15), 9)).containsExactlyInAnyOrder(0, 1)
  }

  @Test
  fun `two sum hash`() {
    val x = Solution()
    assertThat(x.twoSumHash(intArrayOf(2, 7, 11, 15), 9)).containsExactlyInAnyOrder(0, 1)
  }

  @Test
  fun `binary sum`() {
    val x = Solution()
    assertThat(x.sumBinaries("1001", "0010")).isEqualTo("1011")
  }

  @Test
  fun `change bills`() {
    val x = Solution()
    assertThat(x.changeBills(230.0, 500.0)).isEqualTo("Fifty, One Hundred, One Hundred, Twenty")
  }

  @Test
  fun `change bills cents`() {
    val x = Solution()
    assertThat(x.changeBills(15.94, 16.0)).isEqualTo("Nickel, Penny")
  }
}
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

  @Test
  fun `search range`() {
    val x = Solution()
    assertThat(x.searchRange(intArrayOf(5,7,7,8,8,10), 8)).isEqualTo(intArrayOf(3, 4))
  }

  @Test
  fun `search range when not exists`() {
    val x = Solution()
    assertThat(x.searchRange(intArrayOf(5,7,7,8,8,10), 6)).isEqualTo(intArrayOf(-1, -1))
  }

  @Test
  fun `search range at the end`() {
    val x = Solution()
    assertThat(x.searchRange(intArrayOf(5,7,7,8,8,10,10), 10)).isEqualTo(intArrayOf(5, 6))
  }

  @Test
  fun `search range at the beginning`() {
    val x = Solution()
    assertThat(x.searchRange(intArrayOf(5,7,7,8,8,10,10), 5)).isEqualTo(intArrayOf(0, 0))
  }

  @Test
  fun `subarray sums K`() {
    val x = Solution()
    assertThat(x.subarraySum(intArrayOf(1,1,1), 2)).isEqualTo(2)
  }

  @Test
  fun `subarray sums K again`() {
    val x = Solution()
    assertThat(x.subarraySum(intArrayOf(1,2,3), 3)).isEqualTo(2)
  }

  @Test
  fun `subarray sums K edge case`() {
    val x = Solution()
    assertThat(x.subarraySum(intArrayOf(1), 0)).isEqualTo(0)
  }

  @Test
  fun maxArea() {
    val x = Solution()
    assertThat(x.maxArea(intArrayOf(1,8,6,2,5,4,8,3,7))).isEqualTo(49)
  }

  @Test
  fun `maxArea in the middle`() {
    val x = Solution()
    assertThat(x.maxArea(intArrayOf(1,8,6,2,1000,1000,8,3,7))).isEqualTo(1000)
  }
}
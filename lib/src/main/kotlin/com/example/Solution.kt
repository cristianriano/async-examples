package com.example

class Solution {
  fun solution(numbers: MutableList<Int>): MutableList<Int> {
    val result = mutableListOf<Int>()

    for (i in 0..(numbers.size-3)) {
      val (x, y, z) = numbers.subList(i, i+3)

      if ((x < y && z < y) || (x > y && z > y)) {
        result.add(1)
      } else {
        result.add(0)
      }
    }

    return result
  }

  fun twoSum(nums: IntArray, target: Int): IntArray {
    val size = nums.size - 1
    for (i in 0..size) {
      for (j in i+1..size) {
        if (nums[i] + nums[j] == target) {
          return intArrayOf(i, j)
        }
      }
    }

    return IntArray(2)
  }

  fun twoSumHash(nums: IntArray, target: Int): IntArray {
    val hash = mutableMapOf<Int, Int>()
    for ((i, v) in nums.withIndex()) {
      val complement = target - v
      if (hash.contains(complement)) {
        return intArrayOf(i, hash.getOrDefault(complement, -1))
      }
      hash[v] = i
    }

    return IntArray(2)
  }

  fun sumBinaries(x: String, y: String): String {
    return Integer.toBinaryString(x.toInt(2) + y.toInt(2))
  }

  fun changeBills(price: Double, cash: Double): String {
    var change = cash - price
    val bills = mutableListOf<String>()

    for (current in Bill.entries) {
      while (change >= current.value) {
        bills.add(current.text)
        change -= current.value
      }
    }

    bills.sort()
    return bills.joinToString()
  }

  companion object {
//    (100,50,20,10,5,2,1,0.5,0.25,0.1,0.05,0.01)
    enum class Bill(val value: Double, val text: String) {
      HUNDRED(100.0, "One Hundred"),
      FIFTY(50.0, "Fifty"),
      TWENTY(20.0, "Twenty"),
      TEN(10.0, "Ten"),
      FIVE(5.0, "Five"),
      TWO(2.0, "Two"),
      ONE(1.0, "One")
    }
  }
}
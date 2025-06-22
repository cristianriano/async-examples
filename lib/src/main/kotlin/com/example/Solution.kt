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

  fun searchRange(nums: IntArray, target: Int): IntArray {
    val index = findIndex(nums, 0, nums.size - 1, target)

    if (index == -1) return intArrayOf(-1, -1)

    var (begin, end, cursor) = listOf(index, index, index)

    while (nums[cursor] == target) {
      begin = cursor
      if (cursor == 0) break
      cursor--
    }

    cursor = index
    while (nums[cursor] == target) {
      end = cursor
      cursor++
      if (cursor == nums.size) break
    }

    return intArrayOf(begin, end)
  }

  private fun findIndex(nums: IntArray, begin: Int, end: Int, target: Int): Int {
    if (begin > end) return -1

    val mid = begin + ((end - begin) / 2)

    return if (target == nums[mid]) mid
    else if (nums[mid] > target) return findIndex(nums, begin, mid - 1, target)
    else return findIndex(nums, mid + 1, end, target)
  }

  fun numIslands(grid: Array<CharArray>): Int {
    val visited = mutableSetOf<Coordinate>()
    var islands = 0

    for (i in 0..(grid.size-1)) {
      for (j in 0..(grid[i].size-1)) {
        val current = Coordinate(i, j)
        if (visited.contains(current)) continue
        visited.add(current)

        val isLand = grid[i][j] == '1'
        if (!isLand) continue

        visitIsland(grid, i, j, visited)
        islands++
      }
    }

    return islands
  }

  fun subarraySum(nums: IntArray, k: Int): Int {
    var sums = 0
    var prefix = 0
    val prefixSumFreq = mutableMapOf(0 to 1)

    for (n in nums) {
      prefix += n
      sums += prefixSumFreq.getOrDefault(prefix - k, 0)
      prefixSumFreq[prefix] = prefixSumFreq.getOrDefault(prefix, 0) + 1
    }

    return sums
  }

  private fun visitIsland(grid: Array<CharArray>, i: Int, j: Int, visited: MutableSet<Coordinate>) {
    val stack = ArrayDeque<Coordinate>()
    stack.addFirst(Coordinate(i, j))

    while (!stack.isEmpty()) {
      val current = stack.removeFirst()
      if (visited.contains(current)) continue
      visited.add(current)

      val isLand = grid[current.x][current.y] == '1'

      if (!isLand) continue

      if (current.x + 1 < grid.size) stack.addFirst(Coordinate(current.x+1, current.y))
      if (current.x > 0) stack.addFirst(Coordinate(current.x-1, current.y))
      if (current.y + 1 < grid[i].size) stack.addFirst(Coordinate(current.x, current.y+1))
      if (current.y > 0) stack.addFirst(Coordinate(current.x, current.y-1))
    }
  }

  fun maxArea(height: IntArray): Int {
    var max = 0
    var i = 0
    var j = height.size - 1

    while (i < j) {
      var area = (j - i) * Math.min(height[i], height[j])

      if (max < area) {
        max = area
      }

      if (height[i] > height[j]) j--
      else i++
    }

    return max
  }

  fun findMaxAverage(nums: IntArray, k: Int): Double {
    var sum = 0.0

    for (i in 0..(k - 1)) {
      sum += nums[i]
    }
    var maxAvg = sum / k

    var j = 0
    for (i in k..(nums.size - 1)) {
      sum += nums[i]
      sum -= nums[j]

      if ((sum / k) > maxAvg) {
        maxAvg = sum / k
      }

      j++
    }

    return maxAvg
  }

  private data class Coordinate(val x: Int, val y: Int)

  companion object {
    enum class Bill(val value: Double, val text: String) {
      HUNDRED(100.0, "One Hundred"),
      FIFTY(50.0, "Fifty"),
      TWENTY(20.0, "Twenty"),
      TEN(10.0, "Ten"),
      FIVE(5.0, "Five"),
      TWO(2.0, "Two"),
      ONE(1.0, "One"),
      HALF(0.5, "Half"),
      QUARTER(0.25, "Quarter"),
      DIME(0.1, "Dime"),
      NICKEL(0.05, "Nickel"),
      PENNY(0.01, "Penny"),
    }
  }
}
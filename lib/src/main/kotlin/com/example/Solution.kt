package com.example

import java.util.PriorityQueue

class Solution {
  fun zigzag(numbers: MutableList<Int>): MutableList<Int> {
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

  // Leetcode 1
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

  // Leetcode 1
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

  // Given 2 non-negative integers in a binary representation as a string -
  // return sum of them in a binary representation and discard intial zeros (if any)
  fun sumBinaries(x: String, y: String): String {
    return Integer.toBinaryString(x.toInt(2) + y.toInt(2))
  }

  // Given 2 non-negative double numbers first representing price of product (pp)
  // bought by customer and second representing cash given by customer.
  // Cashier has (100,50,20,10,5,2,1,0.5,0.25,0.1,0.05,0.01) as bills.
  // Return bills returned to customer as a comma separated string. String has to be alphabetically sorted.
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

  // Leetcode 34
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

  // Leetcode 200
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

  private data class Coordinate(val x: Int, val y: Int)

  // Leetcode 560
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

  // Leetcode 11
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

  // Leetcode 643
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

  // Leetcode 76
  fun minWindow(s: String, t: String): String {
    val frequencies = mutableMapOf<Char, Int>()
    t.forEach { frequencies[it] = frequencies.getOrDefault(it, 0) + 1 }
    val observedFrequencies = t.map { it to 0 }.toMap().toMutableMap()

    var j = 0
    var i = 0
    var substring = ""

    while (i < s.length) {
      val c = s[i]
      if (observedFrequencies.contains(c)) {
        observedFrequencies[c] = observedFrequencies.getValue(c) + 1
      }

      while (observedFrequencies.all { (char, frequency) -> frequency >= frequencies[char]!! }) {
        val newSubstring = s.substring(j, i+1)

        if (substring == "") substring = newSubstring
        if (newSubstring.length < substring.length) substring = newSubstring

        val c = s[j]
        if (observedFrequencies.contains(c)) {
          observedFrequencies[c] = observedFrequencies.getValue(c) - 1
        }

        j++
      }

      i++
    }

    return substring
  }

  // Leetcode 739
  fun dailyTemperatures(temperatures: IntArray): IntArray {
    val response = IntArray(temperatures.size)
    val stack = ArrayDeque<Int>()

    for ((i, temp) in temperatures.withIndex()) {
      if (stack.isEmpty()) {
        stack.addFirst(i)
        continue
      }

      while (!stack.isEmpty() && temp > temperatures[stack.first()]) {
        val topOfStack = stack.removeFirst()
        response[topOfStack] = i - topOfStack
      }

      stack.addFirst(i)
    }

    return response
  }

  private data class DayTemperature(val day: Int, val temp: Int) : Comparable<DayTemperature> {
    override fun compareTo(other: DayTemperature): Int {
      return temp - other.temp
    }
  }

  // Leetcode 215
  fun findKthLargest(nums: IntArray, k: Int): Int {
    val heap = PriorityQueue(nums.size, Comparator<Int> { o1, o2 -> o2 - o1 })

    for (i in nums) {
      heap.add(i)
    }

    for (i in 1..k-1) {
      heap.poll()
    }

    return heap.poll()
  }

  // Leetcode 56
  fun merge(intervals: Array<IntArray>): Array<IntArray> {
    intervals.sortBy { it[0] }
    val merged = Array<IntArray>(intervals.size, { intArrayOf() })
    var pointer = 0
    merged[pointer] = intervals[0]

    for (i in 1..(intervals.size - 1)) {
      val current = intervals[i]

      // If collide then merge
      if (current[0] <= merged[pointer][1]) {
        merged[pointer][1] = Math.max(current[1], merged[pointer][1])
      } else {
        pointer++
        merged[pointer] = current
      }
    }

    // Remove empty
    return merged.dropLast(intervals.size - pointer - 1).toTypedArray()
  }

  // Leetcode 33
  fun searchRotatedArray(nums: IntArray, target: Int): Int {
    var begin = 0
    var end = nums.size - 1

    while (begin <= end) {
      val mid = begin + ((end - begin) / 2)

      if (nums[mid] == target) return mid
      // Pivot on the right
      else if (nums[begin] <= nums[mid]) {
        if (nums[begin] <= target && target < nums[mid]) end = mid - 1
        else begin = mid + 1
      }
      // Pivot on the left
      else {
        if (nums[mid] < target && target <= nums[end]) begin = mid + 1
        else end = mid - 1
      }
    }

    return -1
  }

  // Leetcode 46
  fun permute(nums: IntArray): List<List<Int>> {
    val response = mutableListOf<List<Int>>()
    backtrackPermutations(response, nums, IntArray(nums.size) { -100 }, 0)
    return response
  }

  private fun backtrackPermutations(
    solutions: MutableList<List<Int>>,
    nums: IntArray,
    currentPath: IntArray,
    index: Int
  ) {
    if (!currentPath.contains(-100)) {
      solutions.add(currentPath.toList())
      return
    }

    val element = nums[index]

    for (i in 0..(nums.size - 1)) {
      if (currentPath[i] == -100) {
        val potential = currentPath.copyOf()
        potential[i] = element
        backtrackPermutations(solutions, nums, potential, index+1)
      }
    }
  }

  // Leetcode 51
  fun solveNQueens(n: Int): List<List<String>> {
    val solutions = mutableListOf<List<String>>()
    backtrackQueens(solutions, n, MutableList(n) { ".".repeat(n) }, 0)
    return solutions
  }

  private fun backtrackQueens(
    solutions: MutableList<List<String>>,
    n: Int,
    currentPath: MutableList<String>,
    row: Int
  ) {
    if (currentPath.all { it.contains('Q') }) {
      solutions.add(currentPath)
      return
    }

    for (column in 0..(n - 1)) {
      // Check if an already existing queen would kill it (only check vertically and diagonally)
      var valid = true
      for (j in 0..(row - 1)) {
        // Vertically
        if (currentPath[j][column] == 'Q') {
          valid = false
          break
        }
        // Diagonally
        val diff = row - j
        if (column - diff >= 0 && currentPath[j][column - diff] == 'Q') {
          valid = false
          break
        }
        if (column + diff < n && currentPath[j][column+diff] == 'Q') {
          valid = false
          break
        }
      }

      if (!valid) continue

      // Make a copy
      val newRow = ".".repeat(column) + "Q" + ".".repeat(n - column - 1)
      val newPath = currentPath.map { it }.toMutableList()
      newPath[row] = newRow
      backtrackQueens(solutions, n, newPath, row+1)
    }
  }

  // Leetcode 53: Kadane's Algorithm
  fun maxSubArray(nums: IntArray): Int {
    var localMax = nums[0]
    var globalMax = nums[0]

    for (i in 1..(nums.size - 1)) {
      localMax = Math.max(nums[i], localMax + nums[i])
      if (globalMax < localMax) globalMax = localMax
    }

    return globalMax
  }

  // Codility Demo
  fun minInt(A: IntArray): Int {
    val minHeap = PriorityQueue<Int>(
      A.size,
      Comparator<Int> { o1, o2 -> o1 -  o2}
    )

    for (i in A) {
      if (i > 0) {
        minHeap.add(i)
      }
    }

    if (minHeap.isEmpty()) return 1

    var min = 1

    while (!minHeap.isEmpty()) {
      if (minHeap.peek() != min) return min
      while (min == minHeap.peek()) minHeap.poll()
      min++
    }

    return min
  }

  // Codility: Find lower num with max digit sum. No trailing zeros
  fun maxDigitsSum(s: String): String {
    var maxSum = 0
    var response = ""

    var left = s[0] - '1'

    val candidate = left.toString() + "9".repeat(s.length - 1)
    var sum = sumOfDigits(candidate)

    maxSum =  sum
    response = candidate

    // Test one less
    val oneLess = (s.toDouble() - 1).toString()
    sum = sumOfDigits(oneLess)

    if (sum > maxSum) response = oneLess

    return response.trimStart { it == '0' }
  }

  private fun sumOfDigits(num: String): Int = num.sumOf { it - '0' }

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
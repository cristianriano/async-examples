package com.example

class Solution {
  fun containsPattern(arr: IntArray, m: Int, k: Int): Boolean {
    val n = arr.size
    var count = 0
    for (i in 0..n-1-m) {
      if (arr[i] == arr[i+m]) count++
      else count = 0

      if (count == m * (k-1)) return true
    }

    return false
  }
}

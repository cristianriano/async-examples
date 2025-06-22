package com.example

class LongestPalindrome {
  fun longestPalindrome(s: String): String {
    var longestSubstring = ""

    for (i in 0..(s.length - 1)) {
      var currentPalindrome = checkAround(i, i, s)

      if (currentPalindrome.length > longestSubstring.length) {
        longestSubstring = currentPalindrome
      }

      currentPalindrome = checkAround(i, i+1, s)

      if (currentPalindrome.length > longestSubstring.length) {
        longestSubstring = currentPalindrome
      }
    }

    return longestSubstring
  }

  private fun checkAround(i: Int, j: Int, s: String): String {
    var (begin, end, maxSize, currentSize) = listOf(i, j, 0, 0)
    var longestSubstring = ""

    while (begin >= 0 && end < s.length && s[begin] == s[end]) {
      currentSize++
      if (currentSize > maxSize) {
        maxSize = currentSize
        longestSubstring = s.substring(begin, end + 1)
      }

      begin--
      end++
    }

    return longestSubstring
  }

  fun isPalindrome(substring: String): Boolean {
    var (i, j) = Pair(0, substring.length - 1)

    while(i <= j) {
      if (substring[i] != substring[j]) return false

      i++
      j--
    }

    return true
  }
}
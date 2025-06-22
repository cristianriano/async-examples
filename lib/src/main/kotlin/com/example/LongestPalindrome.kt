package com.example

class LongestPalindrome {

  // Can be optimized scaning each letter in both directions
  fun longestPalindrome(s: String): String {
    val palindromes = mutableMapOf<String, Int>()

    for (i in 0..(s.length - 1)) {
      for (j in (s.length) downTo i) {
        val substring = s.substring(i, j)
        if (isPalindrome(substring)) {
          palindromes[substring] = substring.length
          break
        }
      }
    }

    val entry = palindromes.maxByOrNull { it.value }
    return entry?.key ?: ""
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
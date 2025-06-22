package com.example

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LongestPalindromeTest {

  @Test
  fun solution() {
    val x = LongestPalindrome()
    assertThat(x.longestPalindrome("bananas")).isEqualTo("anana")
  }

  @Test
  fun isPalindrome() {
    val x = LongestPalindrome()
    assertThat(x.isPalindrome("abccba")).isTrue
  }

  @Test
  fun isPalindromeSingleLetter() {
    val x = LongestPalindrome()
    assertThat(x.isPalindrome("a")).isTrue
  }

  @Test
  fun isNotPalindrome() {
    val x = LongestPalindrome()
    assertThat(x.isPalindrome("abcdba")).isFalse
  }
}
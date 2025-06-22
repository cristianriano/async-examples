package com.example

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LongestPalindromeTest {

  @Test
  fun `longestPalindrome odd length`() {
    val x = LongestPalindrome()
    assertThat(x.longestPalindrome("bananas")).isEqualTo("anana")
  }

  @Test
  fun `longestPalindrome even length`() {
    val x = LongestPalindrome()
    assertThat(x.longestPalindrome("cbbd")).isEqualTo("bb")
  }

  @Test
  fun `longestPalindrome single letter`() {
    val x = LongestPalindrome()
    assertThat(x.longestPalindrome("a")).isEqualTo("a")
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
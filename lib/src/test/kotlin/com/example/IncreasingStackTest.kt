package com.example

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class IncreasingStackTest {

  @Test
  fun `when stack is empty`() {
    val stack = IncreasingStack<Int>()

    assertThat(stack.isEmpty()).isTrue
    stack.push(5)
    assertThat(stack.isEmpty()).isFalse
    assertThat(stack.peek()).isEqualTo(5)
    assertThat(stack.isEmpty()).isFalse
    assertThat(stack.pop()).isEqualTo(5)
    assertThat(stack.isEmpty()).isTrue
  }

  @Test
  fun `when items are added in descending order`() {
    val stack = IncreasingStack<Int>()

    stack.push(3)
    stack.push(2)
    stack.push(1)

    assertThat(stack.pop()).isEqualTo(1)
    assertThat(stack.pop()).isEqualTo(2)
    assertThat(stack.pop()).isEqualTo(3)
  }

  @Test
  fun `when items are added in ascending order`() {
    val stack = IncreasingStack<Int>()

    stack.push(1)
    stack.push(2)
    stack.push(3)

    assertThat(stack.pop()).isEqualTo(1)
    assertThat(stack.pop()).isEqualTo(2)
    assertThat(stack.pop()).isEqualTo(3)
  }

  @Test
  fun `when items are added in random order`() {
    val stack = IncreasingStack<Int>()

    stack.push(3)
    stack.push(5)
    stack.push(1)
    stack.push(2)
    stack.push(1)
    stack.push(4)

    assertThat(stack.pop()).isEqualTo(1)
    assertThat(stack.pop()).isEqualTo(1)
    assertThat(stack.pop()).isEqualTo(2)
    assertThat(stack.pop()).isEqualTo(3)
    assertThat(stack.pop()).isEqualTo(4)
    assertThat(stack.pop()).isEqualTo(5)
  }
}
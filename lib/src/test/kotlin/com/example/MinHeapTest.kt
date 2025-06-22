package com.example

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MinHeapTest {
  private lateinit var heap : Heap

  @BeforeEach
  fun setUp() {
    heap = MinHeap(10)
  }

  @Test
  fun `when heap is empty is initalized correctly`() {
    assertThat(heap.isEmpty()).isTrue
    assertThatThrownBy { heap.poll() }

    heap.add(5)

    assertThat(heap.isEmpty()).isFalse
    assertThat(heap.peek()).isEqualTo(5)
    assertThat(heap.isEmpty()).isFalse
    assertThat(heap.poll()).isEqualTo(5)
    assertThat(heap.isEmpty()).isTrue
  }

  @Test
  fun `when items are added in order`() {
    heap.add(2)
    heap.add(3)
    heap.add(4)
    heap.add(5)

    assertThat(heap.poll()).isEqualTo(2)
    assertThat(heap.poll()).isEqualTo(3)
    assertThat(heap.poll()).isEqualTo(4)
    assertThat(heap.poll()).isEqualTo(5)
  }

  @Test
  fun `when items are descending`() {
    heap.add(5)
    heap.add(4)
    heap.add(3)
    heap.add(2)
    heap.add(1)

    assertThat(heap.poll()).isEqualTo(1)
    assertThat(heap.poll()).isEqualTo(2)
    assertThat(heap.poll()).isEqualTo(3)
    assertThat(heap.poll()).isEqualTo(4)
    assertThat(heap.poll()).isEqualTo(5)
  }

  @Test
  fun `when items are randomly added`() {
    heap.add(4)
    heap.add(1)
    heap.add(3)
    heap.add(2)
    heap.add(5)

    assertThat(heap.poll()).isEqualTo(1)
    assertThat(heap.poll()).isEqualTo(2)
    assertThat(heap.poll()).isEqualTo(3)
    assertThat(heap.poll()).isEqualTo(4)
    assertThat(heap.poll()).isEqualTo(5)
  }
}
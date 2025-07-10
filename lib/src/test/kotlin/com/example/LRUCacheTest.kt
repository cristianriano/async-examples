package com.example

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LRUCacheTest {

  private lateinit var cache: Cache<Int>

  @BeforeEach
  fun setUp() {
    cache = LRUCache(3)
  }

  @Test
  fun `capacity can't be less than or eq 0`() {
    assertThatThrownBy { LRUCache<Int>(0) }.isInstanceOf(IllegalArgumentException::class.java)
    assertThatThrownBy { LRUCache<Int>(-1) }.isInstanceOf(IllegalArgumentException::class.java)
  }

  @Test
  fun `put an item and retrieve`() {
    cache.put("one", 1)
    assertThat(cache.get("one")).isEqualTo(1)
  }

  @Test
  fun `retrieve an unexisting key`() {
    assertThat(cache.get("one")).isNull()
  }

  @Test
  fun `evicts older put element`() {
    cache.put("one", 1)
    cache.put("two", 2)
    cache.put("three", 3)

    cache.put("four", 4)
    assertThat(cache.get("one")).isNull()
    assertThat(cache.get("four")).isEqualTo(4)
    assertThat(cache.get("three")).isEqualTo(3)
    assertThat(cache.get("two")).isEqualTo(2)
  }

  @Test
  fun `evicts older get element`() {
    cache.put("one", 1)
    cache.put("two", 2)
    cache.put("three", 3)
    assertThat(cache.get("three")).isEqualTo(3)
    assertThat(cache.get("two")).isEqualTo(2)
    assertThat(cache.get("one")).isEqualTo(1)

    cache.put("four", 4)
    assertThat(cache.get("three")).isNull()
    assertThat(cache.get("four")).isEqualTo(4)
  }

  @Test
  fun `small cache size`() {
    val smallCache = LRUCache<Int>(1)

    smallCache.put("one", 1)
    assertThat(smallCache.get("one")).isEqualTo(1)
    smallCache.put("two", 2)
    assertThat(smallCache.get("two")).isEqualTo(2)
    assertThat(smallCache.get("one")).isNull()
  }
}
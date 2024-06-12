package com.example

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.streams.asStream

class HelloWorldTest {

  @ParameterizedTest
  @MethodSource("params")
  fun `greets`(name: String) {
    assertThat(HelloWorld().greet(name)).isEqualTo("Hello $name")
  }

  companion object {

    @JvmStatic
    fun params() = sequenceOf(
      Arguments.of("juan"),
      Arguments.of("maria"),
    ).asStream()
  }
}

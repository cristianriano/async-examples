package com.example

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HelloWorldTest {

  @Test
  fun someLibraryMethodReturnsTrue() {
    val classUnderTest = HelloWorld()
    assertThat(classUnderTest.someLibraryMethod()).isTrue()
  }
}

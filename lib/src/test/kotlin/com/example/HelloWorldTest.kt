package com.example

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class HelloWorldTest {
  
  @Test
  fun someLibraryMethodReturnsTrue() {
    val classUnderTest = HelloWorld()
    assertTrue(classUnderTest.someLibraryMethod())
  }
}

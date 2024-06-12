package com.example

class Solution {
  fun maxRepeating(sequence: String, word: String): Int {
    val wordSize = word.length

    var maxRepeating = 0
    for (i in 0..sequence.length) {
      var repeats = 0
      var index = i
      while (sequence.safeSubsequence(index, index + wordSize) == word) {
        index+= wordSize
        repeats++
      }

      if (repeats > maxRepeating) {
        maxRepeating = repeats
      }
    }

    return maxRepeating
  }
}

private fun String.safeSubsequence(start: Int, end: Int): CharSequence = runCatching {
  this.subSequence(start, end)
}.getOrElse { "" }

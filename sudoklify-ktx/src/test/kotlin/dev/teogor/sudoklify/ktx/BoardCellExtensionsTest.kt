package dev.teogor.sudoklify.ktx

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BoardCellExtensionsTest {
  @Test
  fun testZeroToBoardCell() {
    val cell = 0.toJEncodedCell()
    assertEquals("-", cell)
  }

  @Test
  fun testSingleDigitToBoardCell() {
    assertEquals("E", 5.toJEncodedCell())
    assertEquals(2, "B".toInt())
  }

  @Test
  fun testDoubleDigitToBoardCell() {
    assertEquals("Bc", 23.toJEncodedCell())
    assertEquals(64, "Fd".toInt())
  }

  @Test
  fun testTripleDigitToBoardCell() {
    assertEquals("Gdc", 743.toJEncodedCell())
    assertEquals(409, "Dji".toInt())
  }

  @Test
  fun testDashToInt() {
    val value = "-".toInt()
    assertEquals(0, value)
  }
}

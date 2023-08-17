package dev.teogor.sudoklify.model

enum class Type(val rows: Int, val cols: Int) {
  TWO_BY_TWO(2, 2),
  TWO_BY_THREE(2, 3),
  TWO_BY_FOUR(2, 4),
  THREE_BY_THREE(3, 3),
  TWO_BY_FIVE(2, 5),
  THREE_BY_FOUR(3, 4),
  THREE_BY_FIVE(3, 5),
  FOUR_BY_FOUR(4, 4),
  FIVE_BY_FIVE(5, 5),
  SIX_BY_SIX(6, 6),
  SEVEN_BY_SEVEN(7, 7),
  EIGHT_BY_EIGHT(8, 8),
  NINE_BY_NINE(9, 9),
}

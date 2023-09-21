package dev.teogor.sudoklify.model

enum class GameType(
  val sectionHeight: Int,
  val sectionWidth: Int,
) {
  Unspecified(1, 1),
  TwoByTwo(2, 2),
  TwoByThree(2, 3),
  TwoByFour(2, 4),
  ThreeByThree(3, 3),
  TwoByFive(2, 5),
  ThreeByFour(3, 4),
  ThreeByFive(3, 5),
  FourByFour(4, 4),
  FiveByFive(5, 5),
  SixBySix(6, 6),
  SevenBySeven(7, 7),
  EightByEight(8, 8),
  NineByNine(9, 9);

  val size: Int
    get() = sectionHeight * sectionWidth

  override fun toString(): String {
    return "${sectionHeight}x${sectionWidth}"
  }
}

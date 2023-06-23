package dev.teogor.sudoku.gen.utils.token

import dev.teogor.sudoku.gen.types.TokenMap
import dev.teogor.sudoku.gen.utils.helper.sortRandom
import kotlin.random.Random

fun getTokenMap(): TokenMap {
    val tokenList = "abcdefghi".toList()
    val random = Random(sortRandom())
    val shuffledList = tokenList.shuffled(random)
    return shuffledList.withIndex().associate { (index, token) -> token.toString() to (index + 1).toString() }
}
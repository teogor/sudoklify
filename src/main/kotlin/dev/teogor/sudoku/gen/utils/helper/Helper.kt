package dev.teogor.sudoku.gen.utils.helper

import dev.teogor.sudoku.gen.constants.GRID_SIZE
import dev.teogor.sudoku.gen.types.Board
import dev.teogor.sudoku.gen.types.Layout
import dev.teogor.sudoku.gen.types.TokenMap
import dev.teogor.sudoku.gen.utils.layout.populateLayout

fun boardToSequence(board: Board): String = board.joinToString("") { it.joinToString("") }

fun <T> getRandomItem(items: List<T>): T = items.random()

fun getSequence(layout: Layout, seedSequence: String, tokenMap: TokenMap): String {
    val populatedLayout = populateLayout(layout, replaceTokens(seedSequence, tokenMap))
    return boardToSequence(populatedLayout)
}

fun replaceTokens(sequence: String, tokenMap: TokenMap): String {
    return sequence
        .split("").joinToString("") { token -> tokenMap[token] ?: token }
}

fun sequenceToBoard(sequence: String): Board {
    val board = mutableListOf<Array<Char>>()
    sequence.chunked(GRID_SIZE).forEach { chunk ->
        board.add(chunk.toList().toTypedArray())
    }
    return board.toTypedArray()
}

fun sortRandom(): Int = if (Math.random() < 0.5) 1 else -1

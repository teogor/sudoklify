package dev.teogor.sudoku.gen.types

typealias Difficulty = String

typealias Token = String

typealias Board = Array<Array<Char>>
typealias Layout = Array<IntArray>

data class Sudoku(val puzzle: String, val solution: String, val difficulty: Difficulty)
typealias TokenMap = Map<Token, String>

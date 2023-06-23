package dev.teogor.sudoku.gen.utils.seed

import dev.teogor.sudoku.gen.types.Difficulty
import dev.teogor.sudoku.gen.types.Sudoku

fun getSeed(seeds: Array<Sudoku>, difficulty: Difficulty?): Sudoku =
    getRandomItem(getSeedsByDifficulty(seeds, difficulty))

fun getSeedsByDifficulty(seeds: Array<Sudoku>, difficulty: Difficulty?): Array<Sudoku> =
    seeds.filter { seed -> difficulty == null || seed.difficulty == difficulty }.toTypedArray()

fun getRandomItem(items: Array<Sudoku>): Sudoku =
    items[(Math.random() * items.size).toInt()]
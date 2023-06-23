package dev.teogor.sudoku.gen.utils.layout

import dev.teogor.sudoku.gen.types.Board
import dev.teogor.sudoku.gen.types.Layout

fun getLayout(baseLayout: Layout): Layout = shuffleLayout(rotateLayout(baseLayout))

fun getLayoutBands(layout: Layout): Array<Array<IntArray>> = arrayOf(
    arrayOf(layout[0], layout[1], layout[2]),
    arrayOf(layout[3], layout[4], layout[5]),
    arrayOf(layout[6], layout[7], layout[8])
)

fun populateLayout(layout: Layout, sequence: String): Board =
    layout.map { row -> row.map { cell -> sequence[cell] }.toTypedArray() }.toTypedArray()

fun rotateLayout(layout: Layout): Layout =
    getRandomItem(listOf(::rotateLayout0, ::rotateLayout90, ::rotateLayout180, ::rotateLayout270))(layout)

fun rotateLayout0(layout: Layout): Layout = layout

fun rotateLayout90(layout: Layout): Layout =
    layout[0].mapIndexed { index, _ -> layout.map { row -> row[index] }.reversed().toIntArray() }.toTypedArray()

fun rotateLayout180(layout: Layout): Layout =
    layout.map { row -> row.reversed().toIntArray() }.reversed().toTypedArray()

fun rotateLayout270(layout: Layout): Layout =
    layout[0].mapIndexed { index, _ -> layout.map { row -> row.reversed()[index] }.toIntArray() }.toTypedArray()

fun shuffleLayout(layout: Layout): Layout =
    shuffleLayoutColumns(shuffleLayoutRows(shuffleLayoutStacks(shuffleLayoutBands(layout))))

fun shuffleLayoutBands(layout: Layout): Layout =
    getLayoutBands(layout).sortedWith(compareBy { sortRandom() }).flatMap { it.toList() }.toTypedArray()

fun shuffleLayoutColumns(layout: Layout): Layout =
    rotateLayout270(shuffleLayoutRows(rotateLayout90(layout)))

fun shuffleLayoutRows(layout: Layout): Layout =
    getLayoutBands(layout).map { rows -> rows.sortedWith(compareBy { sortRandom() }) }.flatMap { it.toList() }.toTypedArray()

fun shuffleLayoutStacks(layout: Layout): Layout =
    rotateLayout270(shuffleLayoutBands(rotateLayout90(layout)))

fun getRandomItem(items: List<(Layout) -> Layout>): (Layout) -> Layout =
    items.shuffled().first()

fun sortRandom(): Int = if (Math.random() < 0.5) 1 else -1

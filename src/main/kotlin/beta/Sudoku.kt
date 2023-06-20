package beta

data class Sudoku(
    val puzzle: Puzzle,
    val solution: Puzzle,
    val seed: Long,
    val difficulty: Difficulty,
    val sudokuType: SudokuType,
) {
    val size: Int = sudokuType.rows * sudokuType.cols
}
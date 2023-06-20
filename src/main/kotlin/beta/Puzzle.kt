package beta

class Puzzle(
    private val puzzle: Array<IntArray>,
    private val type: SudokuType,
) {
    constructor(type: SudokuType) : this(Array(type.rows * type.cols) { IntArray(type.rows * type.cols) }, type)

    private val size: Int = type.rows * type.cols

    fun setValue(row: Int, col: Int, value: Int) {
        puzzle[row][col] = value
    }

    fun getValue(row: Int, col: Int): Int {
        return puzzle[row][col]
    }

    fun getSize(): Int {
        return size
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (row in puzzle.indices) {
            for (col in puzzle[row].indices) {
                sb.append("${puzzle[row][col]} ")
            }
            sb.append("\n")
        }

        return sb.toString()
    }

    fun formatAsString(): String {
        val sb = StringBuilder()

        for (row in puzzle) {
            for (cell in row) {
                val formattedCell = if (cell == 0) {
                    "_"
                } else {
                    val charList = mutableListOf<Char>()
                    var value = cell
                    while (value > 0) {
                        val digit = (value % 10)
                        val char = if (digit == 0) {
                            ('a' + 9).toChar()
                        } else {
                            ('a'.toInt() + digit - 1).toChar()
                        }
                        charList.add(0, char)
                        value /= 10
                    }

                    charList[0] = charList[0].uppercaseChar()
                    charList.joinToString("")
                }
                sb.append(formattedCell).append("")
            }
            sb.append("\\")
        }

        sb.deleteCharAt(sb.length - 1)
        return sb.toString()
    }

    companion object {
        fun from(content: String): Array<IntArray> {
            val rows = content.trim().split("\\")
            val size = rows[0].length

            val puzzle = Array(rows.size) { IntArray(size) }

            for (i in rows.indices) {
                val row = rows[i].trim()

                for (j in row.indices) {
                    val cellValue = row[j]

                    val cell = if (cellValue == '_') {
                        0
                    } else {
                        val charIndex = cellValue.uppercaseChar().code - 'A'.code
                        val value = (if (charIndex > 9) charIndex - 9 else charIndex) + 1
                        value
                    }

                    puzzle[i][j] = cell
                }
            }

            return puzzle
        }

        fun from(puzzle: IntArray, type: SudokuType): Puzzle {
            val puzzleArray = Array(type.rows) { IntArray(type.cols) }

            for (row in 0 until type.rows) {
                for (col in 0 until type.cols) {
                    puzzleArray[row][col] = puzzle[row * type.cols + col]
                }
            }

            return Puzzle(puzzleArray, type)
        }
    }
}

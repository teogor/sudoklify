package dev.teogor.sudoku.gen

import java.io.File

fun main() {
    val fileName = "src/main/resources/source.txt"

    val file = File(fileName)
    val fileContent = file.readText()
    val puzzle = parsePuzzle(fileContent)
    println("\"$puzzle\",")
}

fun parsePuzzle(fileContent: String): String {
    val stringBuilder = StringBuilder()

    val regex = Regex("<td class=\"[a-zA-Z]*\">(.*?)</td>")
    val matchResults = regex.findAll(fileContent)

    for (matchResult in matchResults) {
        val cellContent = matchResult.groupValues[1]
        val parsedNumber = if (cellContent == "&nbsp;") "-" else getValue(cellContent.toInt())
        stringBuilder.append(parsedNumber)
    }

    return stringBuilder.toString()
}

private fun getValue(value: Int): String {
    var copyValue = value
    val charList = mutableListOf<Char>()
    while (copyValue > 0) {
        val digit = (copyValue % 10)
        val char = if (digit == 0) {
            ('a' + 9)
        } else {
            ('a'.code + digit - 1).toChar()
        }
        charList.add(0, char)
        copyValue /= 10
    }

    charList[0] = charList[0].uppercaseChar()
    return charList.joinToString("")
}
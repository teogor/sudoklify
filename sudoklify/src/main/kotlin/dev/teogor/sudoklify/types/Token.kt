package dev.teogor.sudoklify.types

typealias Token = String

fun Int.toToken(): Token {
  var valueCopy = this
  val charList = mutableListOf<Char>()
  while (valueCopy > 0) {
    val digit = (valueCopy % 10)
    val char = if (digit == 0) {
      ('a' + 9)
    } else {
      ('a'.code + digit - 1).toChar()
    }
    charList.add(0, char)
    valueCopy /= 10
  }

  charList[0] = charList[0].uppercaseChar()
  return charList.joinToString("")
}

fun Token.toNumber(): Int {
  var value = 0
  for (char in this) {
    val digitValue = if (char.isUpperCase()) {
      char - 'A' + 1
    } else {
      char - 'a' + 1
    }
    value = value * 10 + digitValue
  }
  return value
}

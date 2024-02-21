# J-Encoding for Sudoku Cells

This document explains the algorithm used for encoding and decoding cell values in a Sudoku board,
ensuring a concise and human-readable representation.

## Algorithm Overview

The algorithm employs a base-10 encoding scheme with specific mappings for digits:

- **Digits 1-9:** Represented by lowercase letters 'a' to 'i'.
- **Digit 0:** Represented by the letter 'j'.

**Key characteristics**

- **Initial Letter Capitalization:** The first letter in the encoded string is capitalized to
  enhance readability and differentiation.
- **Zero Handling:** The value 0 is represented by a hyphen (-) for clarity and consistency.

## Encoding Process

1. **Convert to String:** The integer representing the cell value is transformed into a string of
   digits.
2. **Map Digits to Letters:** Each digit is replaced with its corresponding letter (a to i for 1 to
   9, j for 0).
3. **Capitalize First Letter:** The first letter in the string is capitalized.
4. **Return Encoded String:** The resulting string is the encoded JEncodedCell representation.

## Decoding Process

1. **Map Letters to Digits:** Each letter in the encoded string is mapped back to its corresponding
   digit (a to i to 1 to 9, j to 0).
2. **Handle Capitalization:** If the first letter is uppercase, it's converted to lowercase before
   mapping.
3. **Combine Digits:** The mapped digits are joined to form an integer.
4. **Return Decoded Integer:** The integer is the decoded cell value.

## **Example**

### **Encoding**

- **Input:** Integer `230`
- **Steps:**
  1. Convert to string: `'2', '3', '0'`
  2. Map digits: `'b', 'c', 'j'`
  3. Uppercase first: `'B', 'c', 'j'`
- **Output:** String `"Bcj"`

### **Decoding**

- **Input:** String `"Bcj"`
- **Steps:**
  1. Split into characters: `'B', 'c', 'j'`
  2. Map letters: `2, 3, 0`
  3. Join digits: `230`
- **Output:** Integer `230`

## Code Implementation

The algorithm is implemented in Kotlin as extension functions for the `Int` and `JEncodedCell` types,
as shown in the provided code snippet.

## Motivation

This algorithm aims to create a compact and human-friendly representation of cell values in Sudoku
boards, enhancing readability and understanding for both developers and users.

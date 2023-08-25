package dev.teogor.sudoklify.extensions

import kotlin.random.Random

fun <T> Random.getRandomItem(items: List<T>): T = items.random(this)

fun Random.sortRandom(): Int = if (nextDouble() < 0.5) 1 else -1

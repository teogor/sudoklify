package dev.teogor.sudoklify

import dev.teogor.sudoklify.model.SudokuBlueprint
import dev.teogor.sudoklify.seeds.fourDigitsSeeds
import dev.teogor.sudoklify.seeds.nineDigitsSeeds
import dev.teogor.sudoklify.seeds.sixteenDigitsSeeds

val SEEDS: Array<SudokuBlueprint> = fourDigitsSeeds + nineDigitsSeeds + sixteenDigitsSeeds

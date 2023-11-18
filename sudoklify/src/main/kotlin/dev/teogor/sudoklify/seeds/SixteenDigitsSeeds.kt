package dev.teogor.sudoklify.seeds

import dev.teogor.sudoklify.model.Difficulty
import dev.teogor.sudoklify.model.GameType
import dev.teogor.sudoklify.model.SudokuBlueprint

/**
 * An array of `SudokuBlueprint` objects representing easy 16x16 Sudoku puzzles.
 */
val sixteenDigitsSeeds: Array<SudokuBlueprint> = arrayOf(
  SudokuBlueprint(
    puzzle = "---C---Af--BAe-E-Ad-F--HE-AdAf-I-Aj--A--AeAAbAcB---AdEH-AfAaE-----FCAa-G-Ab-BIDAe--FAd-E--AaAb-----E----AbAa---------AdH-A-Af-D-AjG--F-A-AaAc-G---CH-Af------F-AE---C-AaI-G-C--AcAa-Aj-Ae-H-AfAd---------EI----A-----IAb--Aj-AfB--HFAfB-Aj-Ae-AbAcAa-----DAcAb-DEF---AjAeAdBH--H--E-Af-AcB-FC--Ab-F-Ad-CAj--I---G---",
    solution = "AaAcDCAjIAAfHAbBAeFEGAdGFAbBHEAaAdAfAcIDAjCAeAIAjAeAAbAcBGCFAdEHDAfAaEHAfAdAeDFCAaAGAjAbAcBIDAeAjAfFAdAcEABAaAbCGIHCEFGBHAbAaAeAfAcIAAdDAjBAdHAbACAfIDEAjGAeAaFAcAIAaAcDGAjAeAdCHFAfBEAbAeDBFAfAEHAbAdCAcAaIAjGAbCEIAcAaGAjFAeAHDAfAdBAjAfGHAdBAeFEIDAaAcAbACAdAAcAaIAbCDAjGAfBEAeHFAfBIAjGAeHAbAcAaEAAdFCDAcAbCDEFIAGAjAeAdBHAaAfHGAEAaAfAdAcBDFCIAjAbAeFAaAdAeCAjDBIHAbAfGAAcE",
    difficulty = Difficulty.EASY,
    gameType = GameType.SixteenDigits,
  ),
  SudokuBlueprint(
    puzzle = "-Ab---AjAa-----G-EFD--AcBAf----AdAe-----------Ab-H--CAeAf-G--Aa---I-Af--AH-B---B-----Aa-G--AAb--Ac--B-H-Ab-C-G-AdAeG-A-IAd----FH---AbC-HAAeF--E----AaDAcAj----I--FAfEAd-DA---AbF----AAj-Ae-AcGA-D-Ad-G-Ab-B--I--BE--H-Ac-----Ab---I-AbF--Aj-B---Aa--Ae-AcGAe--B-Aj-----------CD----FAbB--HAfH-D-----CE---Aj-",
    solution = "HAbAfAdAcAjAaAeCBAIGDEFDACAcBAfHFEGAdAeAjAbIAaEBAjIGAdAAbFHAaDCAeAfAcGAeFAaDECIAcAfAbAjAHAdBFAfAdBAjAcDCHAaAeGIEAAbAaDAcAjAfBEHAAbICFGAeAdAeGEAAbIAdAaAfAjDFHAcBCAbCIHAAeFGAdEAcBAfAjAaDAcAjHGAeAbIBAaFAfEAdCDAAdIAaAbFCAfEDAAjHAeBAcGAFDCAdAaGAjAbAeBAcEIHAfBEAeAfHAAcDGICAdAbAaFAjIAdAbFEHAjAcBDGAfAaACAeCAcGAeIFBAAjAdHAaDAfAbEAjAaAECDAeAfIAcFAbBAdGHAfHBDAaGAbAdAeCEAAcFAjI",
    difficulty = Difficulty.MEDIUM,
    gameType = GameType.SixteenDigits,
  ),
  SudokuBlueprint(
    puzzle = "I--AaF---AcE-A---Aj-------C--DAa-AbB-G----DAAb-AdAe-E-----H----AaC---Af-GAe---A------F-Aa--I-Ae-IC----Af-Ac--Aj-AjG----HIAb-AaB-F-EAa-----Aj---H--CA--AaA--Af---H-----AcF-G-HE-AcAaB----CA-E--Ae-B----AbI-Ad-Ad--B-Ab------H---AI-D---AfH----Ae-----Ad-AaI-AfAeAj----D-AfC-AjAc--D-------E---B-DAd---GA--Af",
    solution = "IAbAeAaFHGBAcEAfACAdDAjAfAAdFEIAeCAjGDAaAcAbBHGCAjAcAfDAAbBAdAeHEIAaFDBHEAdAjAcAaCIAbFAfAGAeBHDAAbAdAfEGCFAjAaAcAeIAbAeFICGAaAEAfAdAcDHAjBAjGAcCDAeHIAbAAaBAdFAfEAaAdEAfAcBAjFAeDHIGCAAbAeAaAAjIAfFGAdHCDBEAbAcFDGAbHEAdAcAaBIAfAeAjCACEAfHAeABAjFAcGAbIDAdAaAdAcIBAaAbCDAAjEAeHAfFGAIAaDGFEAfHAbBAdAjAeAcCAcFBAdAAaIHAfAeAjCAbGEDHAfCGAjAcAbAeDAaAEFBIAdEAjAbAeBCDAdIFAcGAAaHAf",
    difficulty = Difficulty.HARD,
    gameType = GameType.SixteenDigits,
  ),
  SudokuBlueprint(
    puzzle = "----Ad---Ae----AfDCAc--G-AAjF--CAd-AbH-C--BG--D--HAb--Ae--Ae-D-B--Af-Ac--Ad-A--Ab-D--Ae-----Aj-AfAjA-AeHAbIC---EAa-AdFAfDB--G-AaAjAd--AbAI----IAAfAdAj-Aa-D-Ae-BD-Ad-Af-H-ABAjCI----BAfE--AGD-Aa--HAbAdIH-AAj---EAeAbGAf-CAaG-C-----Ad--H-Ac--F-D--H-A--E-Ad-Aj--C--EAj--G--AfH--Ac-EG-AcF--HAjI-Ae--DBAcAj----Af---Ae----",
    solution = "AbAjHFAdIAaAcAeEBAGAfDCAcAfAaGAeAAjFIDCAdBAbHECAdABGEAfDAaFHAbAcIAeAjEAeIDAbBCHAfGAcAjFAdAaAAdFAbAaDAcBAeCHAIEAjGAfAjAAcAeHAbICBAfGEAaDAdFAfDBCFGEAaAjAdAeAcAbAIHHGEIAAfAdAjAbAaFDCAeAcBDAaAdAcAfAeHAbABAjCIEFGAeBAfEICAGDAcAaFAjHAbAdIHFAAjDAcAdEAeAbGAfBCAaGAbCAjBAaFEAdIAfHDAcAAeFIDAfAaHAeAAcCEBAdGAjAbAaCAeAbEAjDIGAAdAfHFBAcAEGAdAcFAbBHAjIAaAeCAfDBAcAjHCAdGAfFAbDAeAAaEI",
    difficulty = Difficulty.VERY_HARD,
    gameType = GameType.SixteenDigits,
  ),
)

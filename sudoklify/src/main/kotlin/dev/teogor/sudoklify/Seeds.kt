package dev.teogor.sudoklify

import dev.teogor.sudoklify.model.Difficulty
import dev.teogor.sudoklify.model.GameType
import dev.teogor.sudoklify.model.SudokuBlueprint

val SEEDS: Array<SudokuBlueprint> = arrayOf(
  // 4x4, digits from 1 to 4
  // Easy
  SudokuBlueprint(
    puzzle = "-BCDCDA-BAD--CBA",
    solution = "ABCDCDABBADCDCBA",
    difficulty = Difficulty.EASY,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "BADCD--AA--DCDAB",
    solution = "BADCDCBAABCDCDAB",
    difficulty = Difficulty.EASY,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "DC-ABA-CCD-BAB-D",
    solution = "DCBABADCCDABABCD",
    difficulty = Difficulty.EASY,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "DCB-BA-CC-AB-BCD",
    solution = "DCBABADCCDABABCD",
    difficulty = Difficulty.EASY,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "C-ABA-CDD-BAB-DC",
    solution = "CDABABCDDCBABADC",
    difficulty = Difficulty.EASY,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "-DABA-CDDC-ABAD-",
    solution = "CDABABCDDCBABADC",
    difficulty = Difficulty.EASY,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "BADC----ABCDCDAB",
    solution = "BADCDCBAABCDCDAB",
    difficulty = Difficulty.EASY,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "----CDABBADCDCBA",
    solution = "ABCDCDABBADCDCBA",
    difficulty = Difficulty.EASY,
    gameType = GameType.FourDigits,
  ),

  // Medium
  SudokuBlueprint(
    puzzle = "D--B-CA--DB-C--A",
    solution = "DACBBCADADBCCBDA",
    difficulty = Difficulty.MEDIUM,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "-BC-A--DB--C-AD-",
    solution = "DBCAACBDBDACCADB",
    difficulty = Difficulty.MEDIUM,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "--CDCD--AB----AB",
    solution = "BACDCDBAABDCDCAB",
    difficulty = Difficulty.MEDIUM,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "DB----BDCA----AC",
    solution = "DBCAACBDCADBBDAC",
    difficulty = Difficulty.MEDIUM,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "--CD--BAAB--DC--",
    solution = "BACDCDBAABDCDCAB",
    difficulty = Difficulty.MEDIUM,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "-BC-A--BC--D-DA-",
    solution = "DBCAACDBCABDBDAC",
    difficulty = Difficulty.MEDIUM,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "C--AD--B-CA--DB-",
    solution = "CBDADACBBCADADBC",
    difficulty = Difficulty.MEDIUM,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "B--A-DC--BA-C--D",
    solution = "BCDAADCBDBACCABD",
    difficulty = Difficulty.MEDIUM,
    gameType = GameType.FourDigits,
  ),

  // Hard
  SudokuBlueprint(
    puzzle = "-CD-D--CC--A-AC-",
    solution = "ACDBDBACCDBABACD",
    difficulty = Difficulty.HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "D-A-A-B--D-A-A-B",
    solution = "DBACACBDBDCACADB",
    difficulty = Difficulty.HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "-DB--BA-D--BB--A",
    solution = "ADBCCBADDACBBCDA",
    difficulty = Difficulty.HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "-D-A-A-D-C-BA-D-",
    solution = "BDCACABDDCABABDC",
    difficulty = Difficulty.HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "-DC--CB-D--CC--B",
    solution = "BDCAACBDDBACCADB",
    difficulty = Difficulty.HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "--CDDC----BCCB--",
    solution = "BACDDCABADBCCBDA",
    difficulty = Difficulty.HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "AB----BABD----DB",
    solution = "ABCDDCBABDACCADB",
    difficulty = Difficulty.HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "A--B-BC--AB-B--C",
    solution = "ACDBDBCACABDBDAC",
    difficulty = Difficulty.HARD,
    gameType = GameType.FourDigits,
  ),

  // VERY_HARD
  SudokuBlueprint(
    puzzle = "-AC---------CDBA",
    solution = "BACDDCABABDCCDBA",
    difficulty = Difficulty.VERY_HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "-C-A--B---C--D-B",
    solution = "BCDADABCABCDCDAB",
    difficulty = Difficulty.VERY_HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "BDCA-----AB-----",
    solution = "BDCAACDBCABDDBAC",
    difficulty = Difficulty.VERY_HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "----ABCD-DB-----",
    solution = "DCABABCDCDBABADC",
    difficulty = Difficulty.VERY_HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "C----A-B-CD-A---",
    solution = "CBADDACBBCDAADBC",
    difficulty = Difficulty.VERY_HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "-CB--A---DA--B--",
    solution = "DCBABACDCDABABDC",
    difficulty = Difficulty.VERY_HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "A--D---B---CD--A",
    solution = "ABCDCDABBADCDCBA",
    difficulty = Difficulty.VERY_HARD,
    gameType = GameType.FourDigits,
  ),
  SudokuBlueprint(
    puzzle = "----DACB-BA-----",
    solution = "BCDADACBCBADADBC",
    difficulty = Difficulty.VERY_HARD,
    gameType = GameType.FourDigits,
  ),

  // 9x9, digits from 1 to 9
  SudokuBlueprint(
    puzzle = "G--D--CAF---G----II-F--HG-BB-IAEDHGC--AFCG--D-G-B-----F-D--ABC---B------C--H-BFIA",
    solution = "GBHDIECAFACEGBFDHIIDFCAHGEBBFIAEDHGCEHAFCGIBDDGCBHIAFEFIDEGABCHHABIFCEDGCEGHDBFIA",
    difficulty = Difficulty.EASY,
    gameType = GameType.NineDigits,
  ),
  SudokuBlueprint(
    puzzle = "G-HEDCF---I-F--A--E--A-----C--I-DEH-I-------G--G--E---A----F--C-CF-E-GI-B-------E",
    solution = "GAHEDCFBIDICFBGAEHEFBAIHCGDCBAIGDEHFIHEBFADCGFDGHCEIABAEIGHFBDCHCFDEBGIABGDCAIHFE",
    difficulty = Difficulty.MEDIUM,
    gameType = GameType.NineDigits,
  ),
  SudokuBlueprint(
    puzzle = "-------HG-----H-D-A-G---EI--CE--DG--DBF---------BFID--HG---F----D--H---C--A-EG---",
    solution = "BEDFIACHGFICEGHBDAAHGDBCEIFICEHADGFBDBFGCEIAHGAHBFIDCEHGBCDFAEIEDIAHBFGCCFAIEGHBD",
    difficulty = Difficulty.HARD,
    gameType = GameType.NineDigits,
  ),
  SudokuBlueprint(
    puzzle = "-BI-------C----E---------AF---EBA-----A-I-G------C--I----H-E--D-E------GC-B--F---",
    solution = "FBIAEGDHCACHDFBEGIEDGCHIBAFGICEBAFDHBHAFIDGCEDFEGCHAIBIAFHGECBDHEDBACIFGCGBIDFHEA",
    difficulty = Difficulty.VERY_HARD,
    gameType = GameType.NineDigits,
  ),

  // 16x16, digits from 1 to 16
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

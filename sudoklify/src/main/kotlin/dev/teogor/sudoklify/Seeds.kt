package dev.teogor.sudoklify

import dev.teogor.sudoklify.model.Difficulty
import dev.teogor.sudoklify.model.Sudoku
import dev.teogor.sudoklify.model.Type

val SEEDS: Array<Sudoku> = arrayOf(
  // 4x4, digits from 1 to 4
  Sudoku(
    puzzle = "ABCD----CDAB----",
    solution = "ABCDDBACBCADCDAB",
    difficulty = Difficulty.EASY,
    type = Type.TWO_BY_TWO,
  ),
  Sudoku(
    puzzle = "BADC----CABD----",
    solution = "BACDADBCDBACCDAB",
    difficulty = Difficulty.MEDIUM,
    type = Type.TWO_BY_TWO,
  ),
  Sudoku(
    puzzle = "CDBA----ABCD----",
    solution = "CDBACDABABCDABCD",
    difficulty = Difficulty.HARD,
    type = Type.TWO_BY_TWO,
  ),
  Sudoku(
    puzzle = "DCBA----BADC----",
    solution = "DCBAADCBBADCABCD",
    difficulty = Difficulty.EXPERT,
    type = Type.TWO_BY_TWO,
  ),

  // 9x9, digits from 1 to 9
  Sudoku(
    puzzle = "G--D--CAF---G----II-F--HG-BB-IAEDHGC--AFCG--D-G-B-----F-D--ABC---B------C--H-BFIA",
    solution = "GBHDIECAFACEGBFDHIIDFCAHGEBBFIAEDHGCEHAFCGIBDDGCBHIAFEFIDEGABCHHABIFCEDGCEGHDBFIA",
    difficulty = Difficulty.EASY,
    type = Type.THREE_BY_THREE,
  ),
  Sudoku(
    puzzle = "G-HEDCF---I-F--A--E--A-----C--I-DEH-I-------G--G--E---A----F--C-CF-E-GI-B-------E",
    solution = "GAHEDCFBIDICFBGAEHEFBAIHCGDCBAIGDEHFIHEBFADCGFDGHCEIABAEIGHFBDCHCFDEBGIABGDCAIHFE",
    difficulty = Difficulty.MEDIUM,
    type = Type.THREE_BY_THREE,
  ),
  Sudoku(
    puzzle = "-------HG-----H-D-A-G---EI--CE--DG--DBF---------BFID--HG---F----D--H---C--A-EG---",
    solution = "BEDFIACHGFICEGHBDAAHGDBCEIFICEHADGFBDBFGCEIAHGAHBFIDCEHGBCDFAEIEDIAHBFGCCFAIEGHBD",
    difficulty = Difficulty.HARD,
    type = Type.THREE_BY_THREE,
  ),
  Sudoku(
    puzzle = "-BI-------C----E---------AF---EBA-----A-I-G------C--I----H-E--D-E------GC-B--F---",
    solution = "FBIAEGDHCACHDFBEGIEDGCHIBAFGICEBAFDHBHAFIDGCEDFEGCHAIBIAFHGECBDHEDBACIFGCGBIDFHEA",
    difficulty = Difficulty.EXPERT,
    type = Type.THREE_BY_THREE,
  ),

  // 16x16, digits from 1 to 16
  Sudoku(
    puzzle = "---C---Af--BAe-E-Ad-F--HE-AdAf-I-Aj--A--AeAAbAcB---AdEH-AfAaE-----FCAa-G-Ab-BIDAe--FAd-E--AaAb-----E----AbAa---------AdH-A-Af-D-AjG--F-A-AaAc-G---CH-Af------F-AE---C-AaI-G-C--AcAa-Aj-Ae-H-AfAd---------EI----A-----IAb--Aj-AfB--HFAfB-Aj-Ae-AbAcAa-----DAcAb-DEF---AjAeAdBH--H--E-Af-AcB-FC--Ab-F-Ad-CAj--I---G---",
    solution = "AaAcDCAjIAAfHAbBAeFEGAdGFAbBHEAaAdAfAcIDAjCAeAIAjAeAAbAcBGCFAdEHDAfAaEHAfAdAeDFCAaAGAjAbAcBIDAeAjAfFAdAcEABAaAbCGIHCEFGBHAbAaAeAfAcIAAdDAjBAdHAbACAfIDEAjGAeAaFAcAIAaAcDGAjAeAdCHFAfBEAbAeDBFAfAEHAbAdCAcAaIAjGAbCEIAcAaGAjFAeAHDAfAdBAjAfGHAdBAeFEIDAaAcAbACAdAAcAaIAbCDAjGAfBEAeHFAfBIAjGAeHAbAcAaEAAdFCDAcAbCDEFIAGAjAeAdBHAaAfHGAEAaAfAdAcBDFCIAjAbAeFAaAdAeCAjDBIHAbAfGAAcE",
    difficulty = Difficulty.EASY,
    type = Type.FOUR_BY_FOUR,
  ),
  Sudoku(
    puzzle = "-Ab---AjAa-----G-EFD--AcBAf----AdAe-----------Ab-H--CAeAf-G--Aa---I-Af--AH-B---B-----Aa-G--AAb--Ac--B-H-Ab-C-G-AdAeG-A-IAd----FH---AbC-HAAeF--E----AaDAcAj----I--FAfEAd-DA---AbF----AAj-Ae-AcGA-D-Ad-G-Ab-B--I--BE--H-Ac-----Ab---I-AbF--Aj-B---Aa--Ae-AcGAe--B-Aj-----------CD----FAbB--HAfH-D-----CE---Aj-",
    solution = "HAbAfAdAcAjAaAeCBAIGDEFDACAcBAfHFEGAdAeAjAbIAaEBAjIGAdAAbFHAaDCAeAfAcGAeFAaDECIAcAfAbAjAHAdBFAfAdBAjAcDCHAaAeGIEAAbAaDAcAjAfBEHAAbICFGAeAdAeGEAAbIAdAaAfAjDFHAcBCAbCIHAAeFGAdEAcBAfAjAaDAcAjHGAeAbIBAaFAfEAdCDAAdIAaAbFCAfEDAAjHAeBAcGAFDCAdAaGAjAbAeBAcEIHAfBEAeAfHAAcDGICAdAbAaFAjIAdAbFEHAjAcBDGAfAaACAeCAcGAeIFBAAjAdHAaDAfAbEAjAaAECDAeAfIAcFAbBAdGHAfHBDAaGAbAdAeCEAAcFAjI",
    difficulty = Difficulty.MEDIUM,
    type = Type.FOUR_BY_FOUR,
  ),
  Sudoku(
    puzzle = "I--AaF---AcE-A---Aj-------C--DAa-AbB-G----DAAb-AdAe-E-----H----AaC---Af-GAe---A------F-Aa--I-Ae-IC----Af-Ac--Aj-AjG----HIAb-AaB-F-EAa-----Aj---H--CA--AaA--Af---H-----AcF-G-HE-AcAaB----CA-E--Ae-B----AbI-Ad-Ad--B-Ab------H---AI-D---AfH----Ae-----Ad-AaI-AfAeAj----D-AfC-AjAc--D-------E---B-DAd---GA--Af",
    solution = "IAbAeAaFHGBAcEAfACAdDAjAfAAdFEIAeCAjGDAaAcAbBHGCAjAcAfDAAbBAdAeHEIAaFDBHEAdAjAcAaCIAbFAfAGAeBHDAAbAdAfEGCFAjAaAcAeIAbAeFICGAaAEAfAdAcDHAjBAjGAcCDAeHIAbAAaBAdFAfEAaAdEAfAcBAjFAeDHIGCAAbAeAaAAjIAfFGAdHCDBEAbAcFDGAbHEAdAcAaBIAfAeAjCACEAfHAeABAjFAcGAbIDAdAaAdAcIBAaAbCDAAjEAeHAfFGAIAaDGFEAfHAbBAdAjAeAcCAcFBAdAAaIHAfAeAjCAbGEDHAfCGAjAcAbAeDAaAEFBIAdEAjAbAeBCDAdIFAcGAAaHAf",
    difficulty = Difficulty.HARD,
    type = Type.FOUR_BY_FOUR,
  ),
  Sudoku(
    puzzle = "----Ad---Ae----AfDCAc--G-AAjF--CAd-AbH-C--BG--D--HAb--Ae--Ae-D-B--Af-Ac--Ad-A--Ab-D--Ae-----Aj-AfAjA-AeHAbIC---EAa-AdFAfDB--G-AaAjAd--AbAI----IAAfAdAj-Aa-D-Ae-BD-Ad-Af-H-ABAjCI----BAfE--AGD-Aa--HAbAdIH-AAj---EAeAbGAf-CAaG-C-----Ad--H-Ac--F-D--H-A--E-Ad-Aj--C--EAj--G--AfH--Ac-EG-AcF--HAjI-Ae--DBAcAj----Af---Ae----",
    solution = "AbAjHFAdIAaAcAeEBAGAfDCAcAfAaGAeAAjFIDCAdBAbHECAdABGEAfDAaFHAbAcIAeAjEAeIDAbBCHAfGAcAjFAdAaAAdFAbAaDAcBAeCHAIEAjGAfAjAAcAeHAbICBAfGEAaDAdFAfDBCFGEAaAjAdAeAcAbAIHHGEIAAfAdAjAbAaFDCAeAcBDAaAdAcAfAeHAbABAjCIEFGAeBAfEICAGDAcAaFAjHAbAdIHFAAjDAcAdEAeAbGAfBCAaGAbCAjBAaFEAdIAfHDAcAAeFIDAfAaHAeAAcCEBAdGAjAbAaCAeAbEAjDIGAAdAfHFBAcAEGAdAcFAbBHAjIAaAeCAfDBAcAjHCAdGAfFAbDAeAAaEI",
    difficulty = Difficulty.EXPERT,
    type = Type.FOUR_BY_FOUR,
  ),
)

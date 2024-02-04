/*
 * Copyright 2023 Teogor (Teodor Grigor)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.teogor.sudoklify.seeds

import dev.teogor.sudoklify.model.Difficulty
import dev.teogor.sudoklify.model.GameType
import dev.teogor.sudoklify.model.SudokuBlueprint

/**
 * An array of `SudokuBlueprint` objects representing easy 9x9 Sudoku puzzles.
 */
val nineDigitsSeeds: Array<SudokuBlueprint> = arrayOf(
  // Remaining Digits 45
  SudokuBlueprint(
    puzzle = "--CE-DG---BDF-GEI----C-B---GAF---IEH---------BCI---ADG---I-H----DBG-ECA---EB-AD--",
    solution = "FHCEIDGBAABDFHGEICEIGCABHFDGAFDBCIEHDEHAGIFCBBCIHEFADGCFAIDHBGEHDBGFECAIIGEBCADHF",
    difficulty = Difficulty.VERY_EASY,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 47
  SudokuBlueprint(
    puzzle = "--BD-GI--DE-----HG---HIE---B-E---D-I--HF-AE--A-D---H-C---CAD---EI-----DH--CI-HA--",
    solution = "CHBDFGIEADEIACBFHGFAGHIEBCDBFEGHCDAIICHFDAEGBAGDEBIHFCHBFCADGIEEIABGFCDHGDCIEHABF",
    difficulty = Difficulty.VERY_EASY,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 48
  SudokuBlueprint(
    puzzle = "-G-F-D-A-B---C---H-E-B-H-C-A-E---F-I-C--I--D-I-G---C-A-I-C-A-B-C---D---G-H-I-E-F-",
    solution = "HGCFEDIABBFAGCIDEHDEIBAHGCFABEDHCFGIFCHAIGBDEIDGEFBCHAEIFCGAHBDCABHDFEIGGHDIBEAFC",
    difficulty = Difficulty.EASY,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 49
  SudokuBlueprint(
    puzzle = "-FBI--HA---IGE-D--D---A---G--D----EF-H-----D-FE----G--A---I---H--H-FAB---BF--CIG-",
    solution = "GFBICDHAEHAIGEFDBCDCEHABFIGBIDAGHCEFCHGFBEADIFEACDIGHBADCBIGEFHIGHEFABCDEBFDHCIGA",
    difficulty = Difficulty.VERY_EASY,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 49
  SudokuBlueprint(
    puzzle = "--HC-FG----ID-HE---D-----I-IF--H--AC---F-B---HE--D--GB-I-----F---EI-GB----FB-DH--",
    solution = "EBHCIFGDACAIDGHEBFFDGEBACIHIFBGHEDACDGAFCBIHEHECADIFGBBIDHECAFGAHEIFGBCDGCFBADHEI",
    difficulty = Difficulty.EASY,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 49
  SudokuBlueprint(
    puzzle = "-D-FEC-BI-F-------E-G-A-C--D----H--BB-F---E-AG--B----C--H-C-B-FF------C-C--GHF-E-",
    solution = "HDAFECGBIIFCHGBDAEEBGDAICFHDCEAFHIGBBHFCIGEDAGAIBDEFHCAGHECDBIFFEDIBAHCGCIBGHFAED",
    difficulty = Difficulty.VERY_EASY,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 49
  SudokuBlueprint(
    puzzle = "-HD-A-EF-G--D----AB-I-C-G-D---A-------A---D--E----D--IA-C-E-B-GH----I--C-GE-B-FI-",
    solution = "CHDIAGEFBGEFDHBICABAIECFGHDIDHAGECBFFBAHICDGEECGBFDHAIAICFEHBDGHFBGDIAECDGECBAFIH",
    difficulty = Difficulty.MEDIUM,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 49
  SudokuBlueprint(
    puzzle = "-----------D-HCI--F--DGE--A-DH-F-E---AIE-HFB---F-C-DI-A--GBI--D--GCE-A-----------",
    solution = "HGAFIBCDEEBDAHCIGFFICDGEBHABDHIFGEACCAIEDHFBGGEFBCADIHAFEGBIHCDIHGCEDAFBDCBHAFGEI",
    difficulty = Difficulty.HARD,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 50
  SudokuBlueprint(
    puzzle = "F--I-D--EG--B-E--D----A----CH-F---BG--I-D-F--AF---B-EI----I----I--D-G--AD--A-C--F",
    solution = "FCBIGDHAEGIABHECFDHDECAFIGBCHDFEIABGEBIGDAFCHAFGHCBDEIBAFEIHGDCIECDFGBHADGHABCEIF",
    difficulty = Difficulty.EASY,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 51
  SudokuBlueprint(
    puzzle = "--GF-E-B----C-A--D----I-H-CFE-----IG--H---E--GC-----HB--D-B----I--A-G----GFI-DA--",
    solution = "CDGFHEIBAHIBCGAFEDAFEDIBHGCFEABDHCIGDBHGCIEAFGCIEAFDHBEADHBCGFIIHCAFGBDEBGFIEDACH",
    difficulty = Difficulty.EASY,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 52
  SudokuBlueprint(
    puzzle = "-------GH------IEBE---IH-F-D---BCE--C---D---F--AIH---D-F--EA---AGE------HD----C--",
    solution = "BCIEFDAGHFHDCAGIEBEAGBIHDFCDIFGBCEHACBHADEGIFGEAIHFBCDIFCDEAHBGAGEHCBFDIHDBFGICAE",
    difficulty = Difficulty.EASY,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 53
  SudokuBlueprint(
    puzzle = "------------EBFD---B--G-I-E-G--F--I--DIC-BGH--F--H--A-F-H-D--G---EHIA------------",
    solution = "DEFIACHBGIHGEBFDCACBADGHIFEHGBAFDEICADICEBGHFEFCGHIBADFAHBDECGIGCEHIAFDBBIDFCGAEH",
    difficulty = Difficulty.MEDIUM,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 53
  SudokuBlueprint(
    puzzle = "C--B---H--HE---B---AB--E--D---D-H-CI---------BC-I-F---D----IHA---C---DI--F-H----E",
    solution = "CDFBIGEHAIHEFDABGCGABCHEIFDEGADBHFCIFIHGECADBBCDIAFGEHDBGECIHAFHECAFBDIGAFIHGDCBE",
    difficulty = Difficulty.MEDIUM,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 53
  SudokuBlueprint(
    puzzle = "-----HFC----AD--IBI-F-C-----EH---IA----------DF-----BEE---H-D-CFC-EB-----H---I---",
    solution = "BDEGIHFCAHGCADFEIBIAFBCEGDHCEHDFBIAGGBAIECHFDDFIHAGCBEEIBFHADGCFCGEBDAHIAHDCGIBEF",
    difficulty = Difficulty.MEDIUM,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 53
  SudokuBlueprint(
    puzzle = "BG--IE----E-HC---F-H-B-G----BH---IC-A-------G------------A-H-G-C--DG--H-----BF-ED",
    solution = "BGCFIEDAHIEDHCAGBFFHABDGEICEBHGFDICAADIEHCBFGGCFIABHDEDFBAEHCGICAEDGIFHBHIGCBFAED",
    difficulty = Difficulty.EASY,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 53
  SudokuBlueprint(
    puzzle = "G--A-I--C----------BD--CEA---GD-BI---D-----B-F--H-E--A-CI--FAG----------H--I-D--E",
    solution = "GEHADIBFCCAFBEHGIDIBDGFCEAHAHGDCBIEFEDCFIAHBGFIBHGECDADCIEHFAGBBFECAGDHIHGAIBDFCE",
    difficulty = Difficulty.HARD,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 53
  SudokuBlueprint(
    puzzle = "--H---D---I-C---A--GD--AIC---I-A--H----H-C----F--I-C---AEF--HI--H---B-G---F---E--",
    solution = "ACHGBIDFEEIBCDFGAHFGDEHAICBCEIDAGBHFDBGHFCAEIHFABIECDGBAEFGDHICIHCAEBFGDGDFICHEBA",
    difficulty = Difficulty.VERY_HARD,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 53
  SudokuBlueprint(
    puzzle = "-EC-I-FB-B--H----CD----A--I--B----F-C-------D-I----A--I--E----FF----I--G-HD-B-IC-",
    solution = "HECGIDFBABAIHFEGDCDFGBCAHEIADBIEGCFHCGHFABEIDEIFCDHAGBIBAEGCDHFFCEDHIBAGGHDABFICE",
    difficulty = Difficulty.VERY_HARD,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 54
  SudokuBlueprint(
    puzzle = "A--D----C-IE---GA---B---F---F---CI-A----D----G-AI---H---G---H---AI---BD-E----B--I",
    solution = "AHFDGIEBCCIEFBHGADDGBCAEFIHBFDGHCIEAIEHBDACFGGCAIEFDHBFBGAIDHCEHAIECGBDFEDCHFBAGI",
    difficulty = Difficulty.EASY,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 54
  SudokuBlueprint(
    puzzle = "-F-----G-H-------B--CEF-I----H--CD--CE--H--IF--AD--H----B-GEC--A-------D-G-----H-",
    solution = "EFIHDBAGCHDGCAIFEBBACEFGIDHFBHIECDAGCEDGHABIFGIADBFHCEDHBAGECFIACEFIHGBDIGFBCDEHA",
    difficulty = Difficulty.MEDIUM,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 55
  SudokuBlueprint(
    puzzle = "-C-H-EG--D-E-----A---B------F--B--D-A--G-F--BI---H---G-----B-I-C-------D--GF-I-A-",
    solution = "FCAHDEGBIDBEIFGCHAHGIBACDEFGFCEBAIDHAHDGIFECBIEBCHDAFGEAHDGBFICCIFAEHBGDBDGFCIHAE",
    difficulty = Difficulty.MEDIUM,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 55
  SudokuBlueprint(
    puzzle = "-E-CI-A------H----G------I--F--------GE--H-DCA-HFCD---------G-D---AE---B-----C-FE",
    solution = "DEFCIGABHBIADHFCEGGHCEBADIFCFDIGEBHAIGEBAHFDCABHFCDEGIECIHFBGADFDGAEIHCBHABGDCIFE",
    difficulty = Difficulty.VERY_HARD,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 55
  SudokuBlueprint(
    puzzle = "-DAI----E-------CF----BH-----DF-E--I--E---C--A--D-GE-----HG----EB-------F----ABG-",
    solution = "GDAIFCHBEIHBAEDGCFCEFGBHDIABGDFCEAHIHFEBAICDGAICDHGEFBDAIHGBFECEBGCDFIAHFCHEIABGD",
    difficulty = Difficulty.VERY_HARD,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 55
  SudokuBlueprint(
    puzzle = "-H-----C-IG--B---F---I-D--B--IH--C---F-----B---A--BE-----D-G---H---F--IE-AF----G-",
    solution = "AHBEGFDCIIGDCBHAEFFECIADGHBEBIHDACFGDFHGCEIBAGCAFIBEDHBIEDHGFACHDGAFCBIECAFBEIHGD",
    difficulty = Difficulty.HARD,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 55
  SudokuBlueprint(
    puzzle = "F-D-H-C------DF---E-----AD-D---G----G--B-E--H----F---AH-I-----D--E-ID-------C-G-E",
    solution = "FADEHGCBIICBADFEHGEHGCBIADFDFAIGHBECGICBAEDFHBEHDFCIGAHBIGEAFCDCGEFIDHABADFHCBGIE",
    difficulty = Difficulty.HARD,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 56
  SudokuBlueprint(
    puzzle = "D----I----CE-GA--F----E---HI------BC-----B-I-F----E---C-H--GB--B-G---A----A-----I",
    solution = "DAFCHIGEBHCEBGAIDFGBIFEDCAHIHDGAFEBCAECHDBFIGFGBICEDHACDHAIGBFEBIGEFHACDEFADBCHGI",
    difficulty = Difficulty.VERY_HARD,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 56
  SudokuBlueprint(
    puzzle = "--B----G--IE------C-----E-F---C-E--B---G--A--F---A--I--------H------IBADEH--BAI--",
    solution = "DFBAECHGIHIEBGFCDACGAIDHEBFADHCIEGFBIBCGFDAEHFEGHABDICBAIDCGFHEGCFEHIBADEHDFBAICG",
    difficulty = Difficulty.MEDIUM,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 56
  SudokuBlueprint(
    puzzle = "---BF--I---------F--G--C-B----F----GF--HD--EIB-E--G----E-----A--C---E--H---DI-F--",
    solution = "EHABFDGICCBDGAIEHFIFGEHCABDAIHFEBCDGFGCHDABEIBDEICGHFAHEICGFDABDCFABEIGHGABDIHFCE",
    difficulty = Difficulty.HARD,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 56
  SudokuBlueprint(
    puzzle = "-E---D-C------I---F-A---D-I--H--AC------B-----IDC--GE-I-F---B-H---A------C-B---F-",
    solution = "BEIHADFCGHDCFGIABEFGAECBDHIEBHGDACIFCFGIBEHADAIDCFHGEBIAFDECBGHGHBAIFEDCDCEBHGIFA",
    difficulty = Difficulty.HARD,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 57
  SudokuBlueprint(
    puzzle = "-D-----EB---GBEI---I---D-H-B---E----------D----I--CH------C---G-B-I-----F-HBA----",
    solution = "CDGHIAFEBHFAGBEICDEIBCFDGHABHFDEIAGCAGCFHBDIEDEIAGCHBFIADECHBFGGBEIDFCAHFCHBAGEDI",
    difficulty = Difficulty.VERY_HARD,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 58
  SudokuBlueprint(
    puzzle = "E--B---G----A-----GC-H----ID-E--H--BH----BDA---------F------E-D--A-F----I------C-",
    solution = "EADBIFCGHFHIACGBDEGCBHDEAFIDFECAHGIBHIGFEBDACABCDGIHEFCGFIHAEBDBDAEFCIHGIEHGBDFCA",
    difficulty = Difficulty.HARD,
    gameType = GameType.NineDigits,
  ),
  // Remaining Digits 58
  SudokuBlueprint(
    puzzle = "--------FC-----G-I--BAF--H-E--GB-A--H-F-C---------------CE-I-D--G-H------A----C--",
    solution = "DEHCIGBAFCFABHDGEIGIBAFEDHCECIGBHAFDHBFDCAEIGADGIEFHCBBHCEGIFDAFGDHACIBEIAEFDBCGH",
    difficulty = Difficulty.HARD,
    gameType = GameType.NineDigits,
  ),
)

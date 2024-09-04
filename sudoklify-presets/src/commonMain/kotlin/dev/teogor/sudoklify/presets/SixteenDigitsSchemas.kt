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

package dev.teogor.sudoklify.presets

import dev.teogor.sudoklify.components.Difficulty
import dev.teogor.sudoklify.components.Dimension
import dev.teogor.sudoklify.schema.SudokuSchema

/**
 * An array of `SudokuSchema` objects representing easy 16x16 Sudoku puzzles.
 */
public val sixteenDigitsSchemas: Array<SudokuSchema> = arrayOf(
  // Remaining Digits 130
  SudokuSchema(
    puzzle = "AdA-----EHD----AeC-----A---B--Af-----Ab-G--H--C-AF-------Aj-AaF-EAe--H--AbCAa-AeEIDGAj-HBAf---AjDAd--G-AeFEICA-EIAe-C-AbBAcAa-HD-Aj-HG-FD-----Af--AeEAcAjAeA--C-----AbE-IB-Ad-IAe-AaAbEH-Aj-AcFA-AaECAjBA-Ae--IGAf---DFH-EIAcAAdB-AaAjAb--Aj--ED-AfI-H-------AcG-Ab--Af--Ad-E-----Ad--G---A-----AAf----CFB-----AaAj",
    solution = "AdAAaAfBFAcEHDAbGAjIAeCAeFHAjAbADCAdBIAcAfAaGEIAcAbEGAdAeHAaAjCAfAFBDDCGBIAjAfAaFAEAeAcAdHAbAcAbCAaFAeEIDGAjAHBAfAdAfBAjDAdAcHGAbAeFEICAAaEIAeACAfAbBAcAaAdHDGAjFHGAdFDAaAjACIAfBAbAeEAcAjAeAAcHCFAdGAfAaAbEDIBBAdAfIAeGAaAbEHDAjCAcFAAbAaECAjBADAeFAcIGAfAdHGDFHAfEIAcAAdBCAaAjAbAeFAjBAbEDAdAfIAcHAaAeACGAaHAcGAAbBAjAfCAeAdFEDICEDAdAaIGAeAjAbAFBHAcAfAAfIAeAcHCFBEGDAdAbAaAj",
    difficulty = Difficulty.VERY_HARD,
    dimension = Dimension.SixteenBySixteen,
  ),
  // Remaining Digits 130
  SudokuSchema(
    puzzle = "---B--F-G---AeAb--GD-FI--B-AjAc-AdACAfIAfHAAeAdAj-CAa--EG-DAdAc---HAAf--I-Aa--FBAd-Af-----I-AaH-----G-H---A-FAd-----AaAHBFAcIAbAeG-----FI-DAd---Ac-----Ab--A-----D---AcG-AfB-----EAbFAfDAGAcAeI-----AjA-G---Ae-E-----GAf-B-----Ab-AAjAc--C-Aj--AdHE---AaAbA-AjAd--IH-GAfDCAcEAeAaBDI-AcC-Aj--AbF-GH--AfE---Aa-Ac--Aj---",
    solution = "EAjAaBAcDFCGAfAdAAeAbHIGDAbFIAaEBAeAjAcHAdACAfIAfHAAeAdAjAbCAaBFEGAcDAdAcCAeGHAAfDAbIEAaBAjFBAdAcAfAAbGAjEIDAaHFAeCAbAeGAjHAfDEACFAdIAaBAcCAaAHBFAcIAbAeGAjAfAdDEFIEDAdCAaAeAcBHAfAAjAbGAeAFAbAaIAdDHEAjAcGCAfBAjHBAaCEAbFAfDAGAcAeIAdAfCIAcAjAHGBAdAbAeDEFAaDEAdGAfAeBAcIFAaCAbHAAjAcGAeCFAjAfAAdHEIBDAaAbAFAjAdAbBIHAaGAfDCAcEAeAaBDIEAcCAdAjAAeAbFAfGHHAbAfEDGAeAaFAcCBAjIAdA",
    difficulty = Difficulty.VERY_HARD,
    dimension = Dimension.SixteenBySixteen,
  ),
  SudokuSchema(
    puzzle = "-BE----Ab-Af-AdHAj-G--Ac-Ae-H--GAAaI----C-F--E-H-Aj--Af-A----AfBAAaC-Ae---Ad-DAbAf-----GFAc-A-------HDG----Aj-I--E--BAaA-Ae-----GF-AjG---I---DC-Ab--AfA--Ac-AeAd---G---CAb-EI-----Af-AdFAj--H--H-A----EAbC-------Ad-GAaB-----AIAe-A---Aa-CFAjIAc----Ac-D--H-E-Ad--G-Aj----AfAbAjAe--C-G-Aa--G-AaED-F-B----AcAf-",
    solution = "AaBEAICDAbAcAfFAdHAjAeGAfAdAcDAeFHAjAbGAAaICBEICAeFAdAcEGHBAjDAaAfAbAAbHGAjAfBAAaCIAeEFDAdAcDAbAfIAjECAdGFAcAeAHAaBFAaAAeHDGAfEAbBAjAdIAcCEAcCBAaAAbAeAdHAfIDGFAjAjGAdHAcIBFAaDCAAbAeEAfADAjAcEAeAdHIAaGBAfFCAbAeEIAaCAbAcDAfAAdFAjBGHBFHGAAfAjIAeEAbCAcAdDAaCAfAbAdFGAaBAjAcDHEAIAeAdABAbGAaAfCFAjIAcAeEHDAcAeDCBHIEAAdAaAfGAbAjFHIFAfAbAjAeAcDCEGBAaAAdGAjAaEDAdFABAeHAbCAcAfI",
    difficulty = Difficulty.VERY_EASY,
    dimension = Dimension.SixteenBySixteen,
  ),
  // Remaining Digits 142
  SudokuSchema(
    puzzle = "--D--AjFG--I---AfAc------AbAa-AjDFH-G----HDAfI-GAa---E-----AaEA-CH---D--Ab---IAe-HEAbB-Af-A-F-AcH-AjAb-------D-AfAe--Aj---Ac---A-AdAa-Af--EIFB-----AeAb-HF-AeG-----AAdBAa--C-EI-Aa---Af---A--AeAb-Aa-------CG-AjH-D-C-A-AcHAjAe-AaAb---B--A---AeD-AfAdAj-----Ad---AjI-FEAeC----F-DCEA-BG------HG---D--ACAa--Af--",
    solution = "AAeDCHAjFGAdAbIEBAaAfAcEIAfBAdAcAbAaAeAjDFHCGAAcAdAbHDAfIBGAaACFEAeAjGAjFAaEAAeCHAfBAcDIAdAbAdDGIAeAaHEAbBAjAfAcACFCAcHFAjAbGAAaAdAeIEDBAfAeAbBAjAfCDAcFEHAGAdAaIAfAaAEIFBAdCAcGDAeAbAjHFHAeGAbIAfAjEAAdBAaAcDCAjEIAdAaGCDAfHAcAbABFAeAbAAaAcBAeEFIDCGAfAjHAdDBCAfAAdAcHAjAeFAaAbGIEBCEAAcHAaAeDIAfAdAjFAbGAaAfAdAbGBAjIAcFEAeCHADIFAjDCEAAfBGAbHAdAeAcAaHGAcAeFDAdAbACAaAjIAfEB",
    difficulty = Difficulty.VERY_EASY,
    dimension = Dimension.SixteenBySixteen,
  ),
  // Remaining Digits 146
  SudokuSchema(
    puzzle = "-FAaG-------ID--Ad-Ac-Af-----AdFE-CBAe------Ad--CAb-IAj-Ac--I-AfDAcFH--Aj-Ab-----H-A--AfI-------Aa--EAf-I--Ac-BAeH-A-Af----Ac-AbC-AaG--ID---Aj--GA--AcAfC--AbHE--DAf--B---AcF--AAc-GF-Ae----Ad-Af-AeAjC-B--F-AdAc--A-------AjA--Af-E-----E-Aj--AaAdBAeC-I--C-AcAa-IAf--H------AeIAb-BFC-----Aj-Ad-B--AjAe-------HAcAa-",
    solution = "AjFAaGAbEAeCBAcAIDHAfAdAbAcDAfAHIAjAaAdFEGCBAeEHAeAGAaAdBDCAbAfIAjFAcAdCIBAfDAcFHAeGAjAAbEAaAcEGHCABDAfIAaAeAdFAbAjFAaCAbEAfGIAjDAcAdBAeHAAAjAfAeFAdHAcEAbCBAaGIDIDBAdAaAjAbAeGAHFAcAfCEGAbHEAdCDAfIAjBAAeAaAcFAaBAAcIGFAbAeEDHCAdAjAfAfAeAjCHBAaEFGAdAcAbDAIDAdFIAcAeAjACAaAfAbEBGHHGEFAjAcAAaAdBAeCAfIDAbCAAcAaDIAfAdAbHAjGFEAeBAeIAbDBFCHAcAfEAaAjAAdGBAfAdAjAeAbEGAFIDHAcAaC",
    difficulty = Difficulty.VERY_EASY,
    dimension = Dimension.SixteenBySixteen,
  ),
  // Remaining Digits 148
  SudokuSchema(
    puzzle = "I-Af---CAjAb-HB----Ab-----AG--CD-------CAc---G----Aj-B-EAj-Ae------Aa-DCG--IFAj-AbAa--AeG---H-C-AbBIE--Aa--A---Aj-HDAfAF--C-----AaBAd-Af----AAjDAc--------FGAeAf----E-IAcAf-----Aa--DAjFGAd-Ab---A--Aj--HGAbD-Ae-Aa---AdD--EI-AAfC--AdAaD-G------Af-HAc-Ac-F----C---AjI-------HAe--AdG-----D----IAd-FDAc---Ab-C",
    solution = "IGAfAaDECAjAbFHBAdAcAAeAbAcBAdAaFAGAjAeCDHIEAfAeDACAcAbIHGAfEAdAaAjFBFEAjHAeAfAdBAcAIAaAbDCGEAIFAjAcAbAaBAdAeGCAfDHGCAcAbBIEDFAaAfHAAeAjAdAjAeHDAfAFAdICAbEAcBGAaBAdAaAfCHGAeAAjDAcFEAbIDHAbAjFGAeAfAaBAdCEAIAcAfBEAcACAaIAeDAjFGAdHAbCIAdAEBAjAcAfHGAbDAaAeFAaFAeGAdDHAbEIAcAAfCBAjAdAaDIGAjBACAbFAfAeHAcEAcAfFAeAbAaDCHEBAjIGAdAAAbCBHAeAcEAdGAaIAjFAfDHAjGEIAdAfFDAcAAeBAbAaC",
    difficulty = Difficulty.EASY,
    dimension = Dimension.SixteenBySixteen,
  ),
  // Remaining Digits 150
  SudokuSchema(
    puzzle = "-AdAe------GD-B--AjF-------Ac----G---B---D-G--AAd-E---AbG-----AjAaEI-AfFAcH---Af-B-A-Ad----I-----AjDABH-Ab--G--AeAjAb--F---IEAf-AdB--D-G-E-CAj----AHED----HB-C-Af-Ab--AcAj-AeDC---A--IBAa--H--Aa-AfAcIAeAb-----Af----I-Aj-Ad-B---FDEAd-AcBAaC-----HAj---C-AdH--Ab-Aa---Af---A----D-------AdG--H-AAb------FAc-",
    solution = "IAdAeAaFAfAcEHGDCBAAbAjFAHEAjAaAdAbAcAfBAeDGICAjBAfAcIDCGFAbAAdAaEHAeCAbGDBAeAHAjAaEIAdAfFAcHGECAfAbBAeAFAdAaAcAjDIAdAfAcICAjDABHAeAbFAaGEAAeAjAbHAcFAaGDIEAfCAdBAaFDBGAdEICAjAfAcAbAeAHEDIAdAFHBAaCAcAfAjAbAeGAcAjAbAeDCGAdEAHFIBAaAfBHFGAaEAfAcIAeAbAjAAdCDAfCAaAAbIAeAjDAdGBHAcEFDEAdAfAcBAaCAeIFAGHAjAbAeAcCAjAdHIFAbBAaGEDAfAAbAaAFEGAjDAfAcCHAeIBAdGIBHAeAAbAfAdEAjDCFAcAa",
    difficulty = Difficulty.EASY,
    dimension = Dimension.SixteenBySixteen,
  ),
  // Remaining Digits 150
  SudokuSchema(
    puzzle = "---E---HD-A--I-----HE---Ab-------CAd---Aa-Ac---I-AG--IB--Ae-Ad-AcHG-E--Ae-DAb--A----F-G-IAf-AdCBFHAb--I-Ac-E--F--Ad-Ac--C----AjAb-AjAa--G-CAc-Ad-B------F-A-AaC-Ac--AbAe-AbH----Aj--F-D--Af--E-A-Ac--AaGAbAeAjC-BD-I-Ab----E--AaH-G--F-AfAbAe-Aj-C--AaH--DC-I---G-Ae---AcF-------B---AbG-----Ac--C-AH---E---",
    solution = "AcAeGEAjAfAbHDAdABFIAaCFAfAHEIGDAbAjAaCAeAdBAcCAdAbDFAaBAcEAeAfIHAGAjAaIBAjAAeCAdFAcHGDEAbAfAeAcDAbAaAjAAfBHEFCGAdIAfGAdCBFHAbADIAjAcAeEAaAFHBAdEAcIAeCGAaAfDAjAbEAjAaIAeGDCAcAbAdAfBFAHGBAjFDAEAaCAfAcHIAbAeAdAbHAeAaCAdAjGIFBDAAcAfEAdEAfAHAcIFAaGAbAeAjCDBDCIAcAbBAfAeAdEAjAAaHFGIAFGAfAbAeEAjBCAcAdAaHDBDCAdIHAaAjGAAeEAbAfAcFHAaEAeAcDAdBAfIFAbGAjCAAjAbAcAfGCFAHAaDAdEBIAe",
    difficulty = Difficulty.EASY,
    dimension = Dimension.SixteenBySixteen,
  ),
  // Remaining Digits 150
  SudokuSchema(
    puzzle = "---BAAbEAd--Aa-----F-AaAcAe---Ad----ECAfAf--G---AaF-HB-Ad-AAb---------G-Ac-B-A-I--------F--H-AcF--HI--CDAbAj----H--Ad---G-BI-A--AeG-AjC--AcDA-Ad--Ab----D--Af-AcEAb--AeG-HC--I-DAd-Aj---E--Ab----AbGAeC--FAd--AjB-Af--E--------F-Ac-E-D-Ae---------AdB-Ab-AdAc-FG---I--AjICF----Ab---AaAfAc-E-----A--IFAeCG---",
    solution = "DICBAAbEAdAfAjAaAcHAeGFFHAaAcAeBGIAdADAbAjECAfAfAeEGAcAjDAaFCHBAbAdIAAbAdAAjCFHAfAeEGIAcAaBDADIEAaAdAbAeAcGAfFBAjHCAcFAeAaHIBACDAbAjAdAfEGHAbAfAdAjCFGAaBIEADAcAeGBAjCAfEAcDAAeAdHAaAbFIAdAjDFBAfIAcEAbCAAeGAaHCGBIFDAdHAjAaAcAeEAAfAbEAaAcAAbGAeCHAfFAdDIAjBAeAfHAbEAaAAjDIBGCFAdAcAaEGDIAeCBAbAcAjAfFHAAdBAAbAfAdAcAaFGHEDICAeAjICFAeGHAjAbBAdAAaAfAcDEAjAcAdHDAAfEIFAeCGBAbAa",
    difficulty = Difficulty.HARD,
    dimension = Dimension.SixteenBySixteen,
  ),
  // Remaining Digits 152
  SudokuSchema(
    puzzle = "Aa----BH-Ad--AG-------C---Af-F-A-Ac-BAb---Aa-AcG---F--AjAfAc----FAe-E-C---AaCAAj-----B--EAe-------Ac--GAa--AjEAfI-AbF-----Af---Ae-AdAH-Af-----H-D-GAc-FAbDAe-CG-Ab-Aj-----H-FIAb-H---Ac-----BG-HAcEAd--BAb--Af-------BAe--I-----EAdAcAe---I-Ac-EA----DAdAc--Af---FC-B---EI-B-Ad-H-C---F-------AbB--Aj-AdI----A",
    solution = "AaEFAjAfBHDAdAbAcAGICAeAdDIAeCAjGAbAfAaFBAHAcEBAbCAEAaAdAcGIAeHFDAfAjAfAcGHAIFAeDEAjCAdBAbAaCAAjAcFAbIAdBAfHEAeGAaDHAdAeDAcACGAaFAbAjEAfIBAbFBGAaDEAfIAcCAeAjAdAHEAfAaIAjAeBHADAdGAcCFAbDAeAdCGAcAbEAjBAaIAfAHFFIAbAaHAfAjAAcCEAdDAeBGAHAcEAdFDBAbAeGAfIAaAjCAjGAfBAeCAaIFHADAbEAdAcAeCHFIGAcAaEAAfAbBAjDAdAcAjAAfDAdAeFCGBAaHAbEIIBEAdAbHACAeAjDFAaAcGAfGAaDAbBEAfAjHAdIAcCFAeA",
    difficulty = Difficulty.MEDIUM,
    dimension = Dimension.SixteenBySixteen,
  ),
  // Remaining Digits 152
  SudokuSchema(
    puzzle = "Ad----AjAbH----------FB-AG--I--AdHAa--H-A-AdF--Ae-C--B---Af-----AjAbAd-C--A-Ab----E-C-Ae--GF-CAc-Ae--H-Ad--EBAAj-AfEG-Ad--------I-HA----DCIG--Ac--------H--CEAjD----AeD-Ac--------G-AjHB-AjCAbD--G-F--Af-AdAa-AfH--F-Aj-Ad----G-Ab--Af-IAeF-----Aa---B--Ab-Aa--AcH-Aj-A--IAdAj--A--GAa-AeAc----------DEAb----C",
    solution = "AdDIAaCAjAbHABGFEAfAeAcAjCFBAfAGAeAcIEDAdHAaAbEHAbAIAdFAcAaAeAfCGDBAjAcAeAfGEBDAaAjAbAdHCFIAIAbBHAjAfEACDAeAaAcGFAdCAcAaAeFGHAbAdAfIEBAAjDAfEGDAdAeAcBAbAFAjAaICHAAdAjFAaDCIGHBAcAbAeEAfBGAAdHAaAfCEAjDIFAbAcAeDFAcIAeAbAdEAfAaCGAAjHBAeAjCAbDAcIGHFABAfEAdAaAaAfHEAFBAjAeAdAcAbDCGIAbAEAfAcIAeFBCAjAdHAaDGGBDCAbEAaAfIAcHAeAjAdAFHIAdAjBCADFGAaAfAeAcAbEFAaAeAcGHAjAdDEAbAIBAfC",
    difficulty = Difficulty.HARD,
    dimension = Dimension.SixteenBySixteen,
  ),
  // Remaining Digits 154
  SudokuSchema(
    puzzle = "---I-Ab-D-----H-CAb--H-AjC--AaI--E-Af--ECB-AAf---FAd-AjAaAf-Aj-Ae------Ad----I-Ac--AeAaFHE-Aj----F--DE-----Ad-Aj--AcAEAa---AjAc---Ab--------C-----AcAa-AdA--FA-AdH-----Af--------I---AaAc---AbCFH--G-Ac-----AE--D----D-GAAbIF--Aa-B----Ab------B-C-AdAaI-AfAc---GH-EAeAj--Ae-Ad--FB--AfAa-D--HE-C-----Ac-Ab-Aa---",
    solution = "AdAFIAaAbAcDAfAjEGBHAeCAbBAeHGAjCAdAAaIAcFEDAfAcDECBIAAfAeAbHFAdGAjAaAfGAjAaAeEFHDBCAdAcIAbAICAcAdAAeAaFHEAfAjAbDBGFAeAbDEGHIBAAdCAjAfAaAcAEAaBAfAdAjAcIGDAbCFHAeGAfHAjCBDAbFAeAcAaIAdAECFAAbAdHAeAaEDBAfGAcIAjBAdDAeIAfEAjAaAcGHAAbCFHAaIGFAcAbBAdCAjAEAeAfDAjAcAfEDCGAAbIFAeHAaAdBDHGAAbAaIEAjFAeBAfCAcAdAaIBAfAcDAdCGHAEAeAjFAbAeAbAdAcAjFBGCAfAaIDAEHEAjCFHAAfAeAcAdAbDAaBGI",
    difficulty = Difficulty.HARD,
    dimension = Dimension.SixteenBySixteen,
  ),
  // Remaining Digits 156
  SudokuSchema(
    puzzle = "Ae-----Ab-FAf--AAcAjE--GAb------AcAeI-D-F---AfAa-G---A--BAdAc--H-Ae-FAj---Ab--Aa-----A-D---Aj----H-AeDAbAd-I-GB----Af--------C---AcAa-II-C-GAc-------DE--AdE-------AfAa-Ae-AcD-FI---C--------Af----EH-D-FAcAdC-Aj----Aj---G-I-----Ad--F---BH-A-Af--CAaG--I---Ae-CF---B-D-CAeG------EAj--AbAcAAe--AfE-Ad-----G",
    solution = "AeIAdAaDBAbHFAfGCAAcAjEABGAbAdCEAjAaHAcAeIAfDFFCAjEAfAaAcGIAbDAAeHBAdAcAfDHAAeIFAjBEAdAbGCAaEFAcAfHACDAdIAaAjGBAbAeHAjAeDAbAdAaIAcGBECFAAfGAbBAEAjFAeCDHAfAcAaAdIIAaCAdGAcBAfAFAeAbAjDEHAjAdEBFIDAAbCAfAaHAeGAcDAeFIAcAfGCEAjAdHBAAaAbAfAAbGBEHAaDAeFAcAdCIAjCHAaAcAjAbAeAdGAIBFEAfDAdEIFAaDAjBHAcAGAfAbAeCAaGAfAjIHAAbAeECFDAdAcBBDHCAeGAdAcAfAaAbIEAjFAAbAcAAeCFAfEBAdAjDAaIHG",
    difficulty = Difficulty.EASY,
    dimension = Dimension.SixteenBySixteen,
  ),
  // Remaining Digits 156
  SudokuSchema(
    puzzle = "Aj----F-G-Af--I--C-Af--CD----AaAeF-HAj---BAfIAa-H---Ab-D-E--Ab-----BD--Ad-------Af-I-AeBG--C--C--E--Ae------Ac--BH-A---AaAcAj---AeF--F--AdAc--E---I-AaG-A---Ae--DC--B--AdAj---HBAa---E-AcI--Ae------Af--B--Aa--I--GAcAf-F-Ae-------Ae--AI-----B--Ac-E-C---Ab-AAdFAa---AcA-HAaAe----AbAf--Aj-Ab--Aj--F-Ae-Ac----I",
    solution = "AjHDAeAdFEGAcAfAAbIAaBCAAfIAcCDAbBGAdAaAeFEHAjCGAdBAfIAaAcHFEAjAbADAeEFAaAbAeAjHACBDIAcAdAfGAaAcEAFAfAjIAbAeBGAdHCDDCAjAdEAaGAeIHFAAfAbAcBAfBHIAAbCDAaAcAjAdEGAeFAeAbFGBAdAcHDEAfCAjIAAaGAaAAfIEAeAjAdDCAcHBFAbAdAjCFDHBAaAAbGEAeAcIAfHAeAcEAbCAFAfAjIBGDAaAdBIAbDGAcAfAdFAaAeHCAjEAFAdAeAaAjAIAfEGHDBCAbAcIEAfCAcBDAbAjAAdFAaAeGHAcAGHAaAeAdCBIAbAfDFAjEAbDBAjHGFEAeCAcAaAAfAdI",
    difficulty = Difficulty.MEDIUM,
    dimension = Dimension.SixteenBySixteen,
  ),
)

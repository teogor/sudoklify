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

import dev.teogor.sudoklify.common.types.Difficulty
import dev.teogor.sudoklify.common.model.SudokuBlueprint
import dev.teogor.sudoklify.common.types.SudokuType

/**
 * An array of `SudokuBlueprint` objects representing easy 16x16 Sudoku puzzles.
 */
val twentyFiveDigitsSeeds: Array<SudokuBlueprint> = arrayOf(
  // Remaining Digits 320
  SudokuBlueprint(
    puzzle = "-----Ah---HAdAc--Ai-BeBb-Af-BaBj------AcC---BAgBj-BbA--HDBa-BcAfBeE-AhAiFAj-Ab-GD--Ba--AeC-AdAaBb-Ag-Ac-Be--H--BaA-BcD---IBG-Ac--Ad-AaG---Af-AgAdAiBb-AaAe-CBc-Ab--D----Af--AgG--H-E-BbB-BdAcAb----C-Ae-D---B-Ae-Ad--AbBc---Bd-Bb-AfAaHAj--A--Bd--Ai-BaAaFAc-----EDBj--B--AcFAdAbIBb-AfBc-Aj-Ag-AaA-CBe-D-BaAiAa-BeAjAe-C--FE------Ah-Ai--Ab--Ai---AaFBa-IAhDBc-B---A-E--Aj-AeF---BbB---AaAbI---Bj-----A--------AjEBdAgAiAf-A-BjBFBcHBb--------Af-----Ac---EBbAb---AeAa---BBe-Bc--Ab-Ae---G-AiAcBdAj-ICBa---Af--B--Ai-Ag------EAd--Ba-AeAbAa-BdAAe-Be-GAc-BjAj-Bd-Ab-AiAh-BcHAdBbBaE--Bb--BcAaH-----AiBaAfE-F--A--C--DCAhF-Be-Ba---AdH--Ag-Aa-I---Bj-Aa-E----BdAfA-CBj-Aj-D--BcB--Be----Ad--F-GH-ID-BeAfBdAh-C---AAb-Ah--Ad-EHI---BeB-AaAg--Bd--G-Bb-Bd-EBaB-AaAe--Ah--GBj-A-AiFBeAd-BjAiIA-AfBdBe--GE-AjAeC---FAg------AaG-Bc-AhC-Bd--AcFH---Aj-----",
    solution = "BcBDAeIAhFAaEHAdAcAjBdAiABeBbAgAfGBaBjAbCAdBdAbAaAcCIAjAeBAgBjGBbAAhAiHDBaFBcAfBeEEAhAiFAjBeAbBcGDIBBaAfHAeCBjAdAaBbBdAgAAcCBeAgBbHBjAfBaABdBcDEAhAbIBGFAcAjAeAdAiAaGBaABjAfAcAgAdAiBbBeAaAeFCBcEAbAjBdDHBAhIAfBcBaAgGDAaHAhEAiBbBIBdAcAbAjBjAdBeCAAeFDEBjAiBAAeAcAdBeBaAbBcCGFBdIBbAgAfAaHAjAhAhABbCBdAgAjAiAbBaAaFAcAdBeAfHAeEDBjIBcBGHAcFAdAbIBbGAfBcAeAjBjAgAhAaABCBeEDBdBaAiAaIBeAjAeBdCBjBFEHAfADBaBcAhGAiAcAdAbAgBbAiBjHBdAaFBaCIAhDBcBeBAdAgGAAfEAbAcAjBbAeFGEDBbBAdAfBeAaAbIAgAeAjBjBaAiAcAhHACBdBcAcAbAeIBaAjEBdAgAiAfCAAaBjBFBcHBbAhBeGDAdAgCAjAfAhHGABcAcFBaBdEBbAbDAdBeAeAaAiIBjBBeAdBcBAAbDAeBbBjAhGHAiAcBdAjAaICBaAgEFAfAjHBAcBjAiBcAgFCBbAhDGEAdIBeBaAAeAbAaAfBdAAeAfBeAgGAcIBjAjBBdFAbAaAiAhCBcHAdBbBaEDIBbGAbBcAaHBDAdAcBeAiBaAfEAeFBdBjAAjAhCAgBdDCAhFEBeAbBaAAjAeAdHBcBbAgAfAaBIGAiAcBjBaAaAdEAiAeAhBbBdAfAAgCBjIAjAcDAbGBcBFHBeBAgAcBcAdBbBjFAjGHAiIDBaBeAfBdAhAbCEAeAaAAbFAhBaCAdAiEHIBjABbBeBDAaAgAeBcBdAfAcGAjBbAjBdHEBaBDAaAeCAfAhBcAgGBjAcAIAiFBeAdAbBjAiIADAfBdBeAcAbGEAaAjAeCAdBaBFAgAhBbBcHAeAfAaGBeBcAAhCAgBdAdAbAcFHBbEAiAjBBjDIBa",
    difficulty = Difficulty.VERY_EASY,
    sudokuType = SudokuType.Sudoku25x25,
  ),
  // Remaining Digits 323
  SudokuBlueprint(
    puzzle = "AjIEBG-Bd-BeAfAb--A-AdAi-BcH---C-----Be-----Ac-Aa-----AhBjAdBAf-H-C-BjFIA--GAf-Aj----BbBAcAbBc---Ae-Ab-BdAj-Bb-H-----Af---EAiGAAa-Af-Bc-AAiAcBa-CBb-H---------Bd---BjAfA-FD----BAcEAjBbHAeAiBa-Ab-Ad-H---C-BaAa-BI-Ab--EBe--Ag-BbAe--B-Aa----CIAgH-Bd-BcAcBj---Ba-D--IAbBeAdAhAf--Aj------C-------EAa-Ba--EAb--HAh-CAg--IAa--AjAfAi-Ac--AgBj-HDCAeA---BbBdAd-------AhAjIG----Ad-Bj-IBeAi--Ba-E--Ab--AaBcAfAh---B-----FAb-AcC-----A---AeAAeBb--Be--Af-B--AgAhD-Bj-I----AdCAfAi-------DIBc---AdAAeBeBj-BbAb--H-CAbBj--GD--AfBc-BaB--BdE--Ag-BeA-------Ad------Ag--AiAaBdIGAc--F-Bb---AiAcA-B-BdGCBc----Aj-Ab--AhBd-C--EAb--Ai-BjF-AcAa-Bc---Bb-Bc-Ag-BdIAfFAeGAjEAb----DBb-HBeA---Ag---------F-EAj-AbBdDG-Ai-Bc-DAjAhAiG---Bb-----A-H-CBe-Ad-Bd---BeAdBaAaBd----I-HAe--GBcAcAg-F-Aa-GAbAeAF-----Ad-Ag-----Aj-----E---AcAj-DAi-Bj--AfAaBa-Be-AhIAbBbA",
    solution = "AjIEBGAgBdDBeAfAbAeAhAFAdAiAaBcHBbBaAcCBjAgBbBaAiBeAeAbEBcFAcGAaCDBdAAjAhBjAdBAfIHAdCHBjFIAAhAaGAfBdAjBaAiBeAeBbBAcAbBcAgDEAeAhAbAcBdAjBjBbBHAgBcBeAdIAfDCBaEAiGAAaFAfAaBcDAAiAcBaAdCBbEHBjBAgAbFIGAeAjBdAhBeBcBjAfAAgFDGBdBeAaBAcEAjBbHAeAiBaIAbCAdAhHAiAcGCBcBaAaBjBIAhAbAfAEBeDAdAgFBbAeBdAjBFAaAeAjBbECIAgHAdBdAiBcAcBjAhAbAfBaADBeGIAbBeAdAhAfAiAcAjAAeBaDBbGCBcBdFBAgBjHEAaBdBaDBbEAbAeAdHAhBjCAgFBeIAaGAAjAfAiBcAcBAbAgBjBcHDCAeAAaEAfBbBdAdAiGBaAcFBBeAhAjIGAjBdFDAdAgBjAcIBeAiAAeBaAhEBBbAbHCAaBcAfAhBeAdIBEHAiBbAjFAbBjAcCBcBdAfAgAaADGBaAeAAeBbAaAcBeBcAbAfBaBHGAgAhDAjBjCIBdFEAiAdCAfAiEBaBGFAhBdDIBcAjAaHAdAAeBeBjAcBbAbAgAcHICAbBjBbAGDAdAaAfBcAeBaBBeAjBdEAhFAgAiBeAAeAjBjHBBcBaAdAhFCDBbAbAgEAfAiAaBdIGAcEAdFBaBbAaAhAgAiAcABeBIBdGCBcHAeDAfAjBjAbDGAhBdAfCBeAjEAbBaAgAiHBjFIAcAaABcAdBAeBbAiBcBAgAaBdIAfFAeGAjEAbAcBjAhAdDBbCHBeABaBaAcAgAfIAhAdBeAeBjCAFBEAjBbAbBdDGAaAiHBcBjDAjAhAiGAfIAgBbBcAcBaAaAbAFHECBeAeAdBBdBbBABeAdBaAaBdAbEAjDIAhHAeAfAiGBcAcAgBjFCAaBdGAbAeAFHCBcAiBbAdBeAgBAcIBjAhAjEBaAfDFECHBcAcAjBDAiBdBjAeGAfAaBaAgBeAdAhIAbBbA",
    difficulty = Difficulty.VERY_EASY,
    sudokuType = SudokuType.Sudoku25x25,
  ),
  // Remaining Digits 325
  SudokuBlueprint(
    puzzle = "-BcAc-IAf-EF-AhAeA-AaBj-------BaAj--Ab--Ai-Ah-Ae--I--Ag----------Ag-BbAeB-DH-GBc--EAi--AdBd-AhBeAAc-Ba-DGI-AAc--BdF-AgBb-AjE-AfAb-BAiAiBd---AgAb--BaDC-B-Bc---Ah-G-FAe-HCB---Ag-AfBdAa--------E--Ah-BcBj----A-Bb--EGAj---Ai-AdAcCAaBeAfAg----BeAd--E--Ba-FH-G--Ai-D-B---IAi--BaG-B---Ah-Aa-DBeAeA--BjBeAfAdAcD--FAb-Ai-AgBb-AhA-C---BcAj-----------AbAAd-BjBa-----Af--AaBjI-Be--Ah-BaAb---Ae------Aj-AcAdBdAb-Aj-Ba-Aa--CAgB-HBeAd--Af-G-Ae-BcCEAf-Ac------Bb---AB-Bj--D-AiFB--Ae-----BjBa-AiBdBc-----------FA---E-AdBb-GH-I-BaC--AhAiBBcDBa--GAfAeAc-Ai-Bb---C-AdBc--FE---Ad-E-B--I-AhBj-D--Bd--AjF----BeBbDAhHBdAj-G---AbAeBc--Ag-Ai----AaAd-Ae--Bc--------EAdAf-B---AjAbG-FAa-E-Ab---Ag-Ai-BeGC--BdAj---AfAAfAi-AhBe-BjC-AaE-AcAd--AbBb-AgHI-Ae-AeAbAgC-DG--IA--AfBa-AiAc-BcAdF-E----------Be--Bj--G-Ae-H--Ai--AAj-------AcAe-CFB-BjBa-AfBc-AhAg-",
    solution = "HBcAcAdIAfBeEFGAhAeAAiAaBjCDBAbBdAgBbBaAjAjBAbAfCAiBdAhBjAeAdBeIAcBbAgFABaGAaBcHDEAaAgFBbAeBCDHAjGBcAbBaEAiAfIAdBdBjAhBeAAcAhBaBeDGIBcAAcAdHBdFBjAgBbAeAjEAaAfAbCBAiAiBdBjAEAgAbBbAaBaDCAjBAfBcHBeAcAhIGAdFAeGHCBAjBjAiAgAeAfBdAaBeDAAcBbAbBcBaEAdFAhIBcBjBaFAbAhABBbDIEGAjHAeBdAiAgAdAcCAaBeAfAgAAeAaAhBeAdBcCEAcAfBaAbFHAjGIBjAiBdDBbBEBbBdIAiAcAjBaGHBAdBcCAhFAaAfDBeAeAAgAbBjBeAfAdAcDAaIFAbBdAiBjAgBbAeAhAECBBaHBcAjGBdGDBcFBbBAjAgAiAbAAdIBjBaAcAhAeCBeAfEHAaBjIBbBeAEAhHBaAbCFAfAeDAaBcAgGAiAjBAcAdBdAbAhAjAiBaFAaAcACAgBEHBeAdDBdAfBbGBjAeIBcCEAfAgAcAdAeBeBdBcAaBbAhGAjABHBjIAbDBaAiFBAdHAeAaGDAfIBjBaAcAiBdBcAjBeFAbEAgBbACAhAcFAAjAgBdEBjAdBbAfGHAaIAbBaCBeAeAhAiBBcDBaBeAaGAfAeAcAbAiABbAjBAhCIAdBcHDFEBdBjAgAdCEAbBBaHIBcAhBjAgDAAiBdGAaAjFBbAeAfAcBeBbDAhHBdAjAfGBeBFAbAeBcAcEAgBjAiACBaIAaAdIAeAiBjBcCAgAaDFBeBaBdEAdAfAhBBbAcAAjAbGHFAaBEHAbBaAeAhAgBcAiBbBeGCIAdBdAjDAcBjAfAAfAiBcAhBeABjCAjAaEDAcAdBdBAbBbFAgHIGAeBaAeAbAgCBjDGBdBIAHAaAfBaBeAiAcAhBcAdFAjEBbDAcIBaBbBcFAdAfBeAjAhBjAgAbGEAeAHBAaAiBdCAAjGBdAdHBbAiEAcAeICFBDBjBaAaAfBcBeAhAgAb",
    difficulty = Difficulty.EASY,
    sudokuType = SudokuType.Sudoku25x25,
  ),
  // Remaining Digits 333
  SudokuBlueprint(
    puzzle = "AaF-------------DAhBjAe-AgBa--B-H-AGAh-Aa----BE----AgAd-Bc--AbAgB-I--E-AbCAe-Ah--H-Be-ABjGAaAd-BaAe--AjBb-FH--AgAd-AfAb-E-Aa--A----DAdBd--Ag-BjAb--Ac--BaAiAjC------Ai----BjB-F---BaAg--A--Ae-Ah-HAj-F--Bc-Bd---ABbG--Af---Ba-Ab--A--EAcAjH--AdBcAaAiBj--Bb-BaAeBAgFG--AgAhBd--Ac-BbGCE---Ad-F-BcAj-AiAAaG-B-BjAa---I-HBeAj-Bd------Ad----G--Bj--AcAhB-Ba-CAjAg-Be-Bb-BdAe-Be----ABcCAi-Aa-------F--Ah---BbBa-Ah-----Ag-----A-----Aa-HAi---Ai--Ae-------Bc-AcBdAhBa----C-BcAe-C-Bd-BaDA-Bj-GAfH--Ai--E----Bb------A-CAfAa-I---AbAi-G-AjAfAbAj-FB-Bj-Bd---AgBeAiAe-D--CAcAh--BdBaAgIGAi-Aa--AbFAhAcBc--AAjAdAe--Bb--Ai-Aa---Aj--AeAAd---Ac-Ag--I-EE-A-D--H--BbAj---G-AdBd----Ag------HGDBj--Ac--AbBa-I--EAjAf----E--I-Bc-BAh-AjAf--GH-FA--BaAc-BjAaFBaAc-Aj-E--I-HBbD-B--Be-CAdAb--H-AgBe----BaAa----Af-EBdI-Bb-I--AfBb-BaAdBdAi-------------FAe",
    solution = "AaFBcAbEAiIGAdAcHBeBdCADAhBjAeAfAgBaBbAjBAcHBeAGAhDAaAeAjIBjBEBaFBdBbAgAdAfBcCAiAbAgBAfIAiBdEBaAbCAeFAhBbAjHBcBeAcABjGAaAdDBaAeCBjAjBbBFHBcAiAgAdDAfAbIEGAaAhBdAAcBeAhBbDAdBdAfAAgBeBjAbBcGAcAaBBaAiAjCIHAeEFBcAiAbBbAdCBjBEFAfBdDBaAgAaAjAIGAeAcAhBeHAjAaFCAeBcAgBdDBeAcABbGAdAhAfBAiHBaEAbIBjABeIEAcAjHAbAfAdBcAaAiBjAhCBbDBaAeBAgFGBdDAgAhBdHBaAcAeBbGCEAbIBAdBeFBjBcAjAfAiAAaGAfBBaBjAaAhAiAIFHBeAjAeBdAbAgEAcCBbAdDBcFEGAaABjAbAfAcAhBAiBaHCAjAgBcBeDBbAdBdAeIBeAcBjDAgABcCAiHAaBbEBdIAeAdAbFBGAhAjAfBaBbBaBdAhAbBeAjEBAgAdAfAcAeFABjGCIBcAaDHAiBIHAiAfAdAeBbGAaAjDAgAbBcEAcBdAhBaFABeBjCAdBcAeAjCFBdIBaDAAhBjBeGAfHAaBbAiAcBEAbAgAeAdBbBeBcEFAcAgABaCAfAaBjIBAhHAbAiDGBdAjAfAbAjGFBAdBjBcBdEIHAgBeAiAeBaDBbAaCAcAhAHBdBaAgIGAiBeAaAfDAbFAhAcBcECAAjAdAeBjBBbBjCAiBAaDBbAhAjBaGAeAAdBdBeFAcAfAgHAbIBcEEAhAAcDAeCHIAbBbAjBcBAiGAaAdBdBjBeFBaAgAfCAAgBcBHGDBjBbBdAcAeFAbBaAiIAdBeEAjAfAaAhBdDEAeBeIAaBcCBAhAdAjAfBbBjGHAbFAAiAgBaAcAiBjAaFBaAcAfAjAhEAgGIAHBbDAeBBdAbBeBcCAdAbAjAdHAhAgBeAFAeBjBaAaAiDAcCAfBcEBdIBBbGIGAcAfBbAbBaAdBdAiBeBCBcEAgAAjAaAhDBjHFAe",
    difficulty = Difficulty.EASY,
    sudokuType = SudokuType.Sudoku25x25,
  ),
  // Remaining Digits 337
  SudokuBlueprint(
    puzzle = "--BjBd-Ah----Ba-Aj-----DCAdGAb-BcAhAcBcFAaAe--Bd-D----Bj---Be-AfCH-Aj-Be-AdAa-BA-GI-Bc---Bb------Bd-AIAe--Aj----E-C--Ad-F-Ah--BbBa---GD-AdBj-FBbABd--BcIAh-Ba--Aj-BEBAh-----C-BeBdAe---AbF-Bb--Bc--Ai--Ab-IAh------AfAjEBcAeC-Bd-Ac--IFBbAd-BcAiAeBaHAgAb-----------AjBe-Bj-----AgF-Bc--Ba--Bd----D-GBb-----BbBd-Be-E---AdAaG--BjBa---AgBe-FB-Ad---DAbAi-Bb---Bd---E------AjBj-AaBc-AiBdAc--AhD--Ad---AeCA----AAfAeGI--Bc-Aj--BAiAbFBe----AdBbH---Ab--BdC--AgAeAf-BcE-BAa------Ag---Ah---Af-BAaH---Ae-FBa-AdG---BAi--BcAaI---C-F-BjAb-----FAd-Bj----G--Bb--Ac-EI-----Ah-BdH-----------AdBcAiAAcAgD-BIBaAf--E-Ag-IBdAbAcAiG------BbAh-Be--F--Ai--E-FAg---AbAaB-Aj-----BdAcCAe-A--Ab-CAjBj--AfIAiBd-EB-BbBa---BaG--E-Bc-Ai--Bj-Be----A--AbBAd-Aa------I---B-AbBb-BaBj-AiAg-G-Ae-CBAi-Be---Bb----E-H--AaAjBdAfDBjBj-AdBbAbBdAg-----Ac-G----Af-IAa--",
    solution = "BAgBjBdBbAhBeAiAfIBaAaAjHFAcAeADCAdGAbEBcAhAcBcFAaAeBbEBdBaDAdBAiAbBjAgGAjBeAAfCHIAjBaBeAfAdAaAcBACGIAhBcAgAbAiBbHEAeBjDFBdHAIAeAiGAjAbDBcAcEBjCAfBAdAaFBdAhAgBeBbBaAbECGDAgAdBjHFBbABdAeBeBcIAhAfBaAiAcAjAaBEBAhHGABjAdCAjBeBdAeAcDAgAbFBaBbAfAiBcIAaAiBeAgAbBaIAhDAaGBFBbAfAjEBcAeCABdAdAcBjHIFBbAdBdBcAiAeBaHAgAbAaGBjAhAfDAcBCAEAjBeAcBjAaCAeBAfAgFEBcHAiBaAAjBdAdBeIAbDAhGBbAAjDBcAfBbBdAcBeAbECIAhAdAaGHAiBjBaAeFBAgBeAeFBAhAdHAjBjDAbAiBaBbIACBdAaAcBcEAgAfGAfAbGAjBjBaAaBcBAiBdAcFEAhDBbBeAdAgIHAeCACAaAcEAAfAeGIAgBjBcAdAjHBaBAiAbFBeAhBbBdDAdBbHBaIFAbAAcBdCDBeAgAeAfAhBcEGBAaBjAiAjDAiBdAgBcCEAhBbBeAAfGBAaHBjAjIAeAcFBaAbAdGAhAeAcBAiBaBeBcAaIAjHBdCAdFAfBjAbDBbAAgEFAdBaBjCHBAfGAAeBbAgDAcBeEIBdBcAaAjAiAhAbBdHAbAaAjBjCBbAeAhFBeEAdBcAiAAcAgDGBIBaAfBcAfEDAgAjIBdAbAcAiGABjBaCAaBBbAhHBeAdAeFBbIAiABeEDFAgAdAfAhAbAaBGAjBaAeHBjBcBdAcCAeBcAAhFAbGCAjBjAaAgAfIAiBdDEBAdBbBaHBeAcBaGAfIEDBcAaAiAeHBjCBeBdBbAcAgAAjFAbBAdAhAaBdAjBeHAcAIEAfAdBDAbBbFBaBjAhAiAgCGBcAeAgCBAiAcBeFBaAdBbAhAeBcAEIHAbGAaAjBdAfDBjBjDAdBbAbBdAgHAhBAjBaAcFGAeBeCBcAfEIAaAAi",
    difficulty = Difficulty.VERY_EASY,
    sudokuType = SudokuType.Sudoku25x25,
  ),
  // Remaining Digits 338
  SudokuBlueprint(
    puzzle = "AfBbIAg----GHAbAAc--Ad--Ae-Ba--BeBc---D-Ab---BAe------AiAj--HF-----Be-IBjBcAi----AdBd-Af-Ag--AjAbC------F-AdAa--B---Bc-Bj--BbE----BBjBdE-AeAfAcAjIAg-Ai-AbH---Ah-A--Ae----Ah-I-AAfAb-Ac--------BcE-AbAcBd---AjAiAgG-H-BaAdD--Ah-I-BeFBj------BcAaF-B-AhICHAgAj-Ab-AiBd----AaAi--C-Ad-E-BeA-B-Af--AeAg------Bj-E-D-BbFBc------AcC-BaAh-AfBd-Ai--BAa-DAb-IA--Aj----Bc--Bb-BaAd--------CAi------Bb-BdA--AbBcAh----FAeEBj-AfAdBd-AgICG----AjDH--AaC-Ai------AhF--------IAd-Aj--I----Ba--BjBc-AcD-AfBd--C-AaAi-GAh-BbH------DBaI-F-B-Be------HBd--Ag-I-AhF-B-C-E--AbBb----BAj-Ba-BjBcAiHAcI-A-GAgAb------AaFD-Ab-Bd--AhAiAd-Bb-BeAjAeH---IBaA-BjAg--------E-AfGBb-D-Bc----B--Af-Ae---AjBc-Ba-AgAaCAiIF-AbDAdBj----ABe--H-Ag---F--AbAd-Bb------AbCAc--E-Ba-BjAe----BBcBdAj-Aa-----FBa--GAh------AfAe---E-I---BAa--G-Be--F--EAjBbAhBj----AfAeABa",
    solution = "AfBbIAgAaCAhBdGHAbAAcEAjAdFBAeDBaBjAiBeBcCBcAhDAdAbAgABbBAeAaBaBjBeEBdAiAjGAfHFAcIGABaBeAcIBjBcAiEDFHAdBdAaAfBbAgAhBAjAbCAeAjAiAbAeHFBaAdAaBeGBAfCAhBcABjIAcBbEAgDBdFBBjBdEDAeAfAcAjIAgBbAiBcAbHBeCBaAhAdAAaGAeDHCAgAhBIBjAAfAbBdAcAiFBaGBeAdAaBbBcEAjAbAcBdBcBAfAjAiAgGBbHAaBaAdDAeEAhAICBeFBjABaEBbAfBeBcAaFAeBDAhICHAgAjBjAbGAiBdAdAcAhGAaAiFAcCBaAdBdEAjBeABjBBbAfBcIAeAgDAbHIBeAdBjAjEAbDHBbFBcAgGAeAiAaBdAcCABaAhBAfBdAfAiGBjBAaCDAbBeIAAeHAjEBaAdFBcAhAcBbAgBaAdBeFDAgIGAhAcCAiAjBEBjBcAaBbHBdAAfAeAbBcAhBbAbAHFAeEBjAaAfAdBdAcAgICGAiBeBBaAjDHAgAcAaCBcAiAjBdAfBaBbDAhFABeAbBAeEGBjIAdEAjAeBIAdBbBeABaAgGBjBcAbAcDAhAfBdHFCAiAaAiEGAhBcBbHAcAbAaBdBjAeDBaIAdFABAgBeAjAfCAdAeAHBdAjDAgAfIBcAhFBeBBaCAcEAaBjAbBbGAiBeCBAjBbBaAdBjBcAiHAcIAaABdGAgAbAfDAeEAhFAaFDAfAbGBdEBAhAiAdCBbAgBeAjAeHBjAcBcIBaAAcBjAgIBaAeAFBeCAjEAbAfGBbAhDAiBcAdBdAaHBBbHAfEAeBdAcBAjBcABaGAgAaCAiIFBeAbDAdBjAhBjIAjABeAaAfHAeAgAhCBcFDGAbAdBaBbAiAcBBdEDAbCAcAhAEBbBaAdBjAeAiHIAfBBcBdAjFAaGAgBeAgBdFBaAiBjGAhCDAdBeBAbAfAeAcAAaEAjIHBcBbBAaBcAdGAiBeAbIFAcBdEAjBbAhBjHDAgCAfAeABa",
    difficulty = Difficulty.VERY_HARD,
    sudokuType = SudokuType.Sudoku25x25,
  ),
  // Remaining Digits 339
  SudokuBlueprint(
    puzzle = "HFG----Ah----AcBj--C-AAb--AeAa-----Aa-BeE---F-Ba-IBcBAh--Bb---E---AhGBb---D-------F----Ai----AbAd--BcFAc-EAh-Aj-BeDAgAf---GB--Ae-BdDAa--CBcBb----Ba--Ad-----Ag--AdBaBeFD---BcA-BbHBG---IAi----EBd-B---Bc--AiC-D---I---BeAc--Af-GAg--AiABj-BaHBd------FEBb------E-AbBbG-Aa-Ad---A-Ba-Bj-Aj---Aa-FBj--Ba-AgB-BeG-EBbAc-AfAh--Bc--AbAfI-GBaAeDBe-B---AcAiE---AjAgAaD-Bd--C-Bb-Be-Ba-------AhAe-G-E-EAhAiBAj--I-Ae---C-Ag--DBjAbBcAc-Aj-Ag-CAb-------G-Bc-Ba-Aa--Be-HBjGBa---EAgAh---Aa-AbAjABdBe-BbAdC--Af--BAj-AgBeBc-BaBj-AbA-D--BbAc-Aa---Bd-E-H-Aj---Ad-Ah-AgIAb-Ai------AgCBa------BdAiD-AfAcAe--BeI-Bj--AdAh---Bd---Aa-CBe--Ba---Bc-AiA----AcBj---CEAiAj-IF---AdGBBdD--Ah-----Ac--D----IAaAd--BaABe-F--AcBc---AdBaBdC-A-AbAi-FAhAj--AgAe----A----Ab-------Ba---AfAcAi---C---C--BjGAE-Ae-Ac---HB-D-----BjBb--AeAj-H--AgBc----Aa----AcAAd",
    solution = "HFGBbDBaAdAhAfAjAiBeAcBjBBdCEAAbBcAgAeAaICAfAAcAaAiBeEAgAeAbFBdBaHIBcBAhGAdBbDBjAjEBeBcAgAhGBbBjBBdDICAfAdAaHAeFAjBaAcAbAiAAiBaBjAbAdAIBcFAcAaEAhAeAjBbBeDAgAfHCBdGBBIAeAjBdDAaHAbCBcBbGAAgBjBaAcAiAdEBeAfAhFAgAcAjAdBaBeFDAaAfAhBcAEBbHBGAbBjCIAiBdAeBbHEBdBjBAhAdAjBcAfAbAiCAeDFAgAaIGABaBeAcAbBAfBcGAgCIAiABjAcBaHBdAhAjBeAdAeAaFEBbDAhCAiBeAeEAcAbBbGIAaFAdDAfBdABcBaBBjHAjAgADAaIFBjBdAeBaHAgBAjBeGAiEBbAcCAfAhAdAbBcFAdAbAfIBcGBaAeDBeAhBBbBjCAcAiEHABdAjAgAaDAaBdHBcCABbAdBeAcBaAgAjAiBAbIBjAhAeAfGFEBeEAhAiBAjHAaIFAeAAdBdCGAgAfBbDBjAbBcAcBaAjBbAgACAbAiAcBdBjFAfEGIBcAdBaAeAaAhBBeDHBjGBaAeAcAfEAgAhBHDAaBcAbAjABdBeFBbAdCIAiAfAiFBAjIAgBeBcAdBaBjAeAbAEDAhBdBbAcHAaCGAaBdDEAHAeAjBjBaGAdBbAhAcAgIAbCAiFBcBAfBeBcAgCBaHAhBAGAbBdAiDAaAfAcAeFAjBeIEBjAdBbAdAhIGBbBdDFAcAaBCBeAgEBaAfBjHBcAjAiAAeAbAeAbAcBjBeBbAfCEAiAjHIFBcAAaAdGBBdDAgBaAhBdAeBAhAbAcBcAiDAgBbAjHIAaAdBjCBaABeGFEAfAcBcHAaAfAdBaBdCIAGAbAiBeFAhAjDEAgAeBbBBjIAAdFAgAaAbBBeBbEBdBjDBaAeGBcAfAcAiAjAhHCBaAjBeCAiFBjGAEAdAeAfAcAhAbBbHBAgDAaIBcBdGBjBbDEAeAjAfHAhCAgBcBFBeAiAaIBdAbBaAcAAd",
    difficulty = Difficulty.HARD,
    sudokuType = SudokuType.Sudoku25x25,
  ),
  // Remaining Digits 340
  SudokuBlueprint(
    puzzle = "AjAgGHBbAd-Bj---IAAi--Be---AcAeD---Bd--Ah-Aj-----AaAfEAe--FD--Bb-AdAi--AeAf-GDBe-Ad-Ag-CBd--Ah----E-IBj---Ah-A------Aj--Bb---Ba---Aa-C--EAe--FAh----HBa--AgAbAfI-AiAcCAa--G---E---Aj--Bb-Ab-FAh---------I---EBaAd-GBeAeAi----AfAbAj-AeAd--Ab--F--B-AcAiAf-D---I---Ah---Ag----Ai----I-Ad-AjBj-BbBe-D-AiAbBaA--C-AeBbAgBdAa-FG----Ac--BABeAg-G--I-----HAhC-BD-E-BcAe-Ad--Bc--AhBa-Af-FE-Ac----Be--Ab-AaFAa-Af--AbAi-G--D--I-AhAd--H-BaAgAe-Bj--Ac----Aj-AiBe-Bb-FBc--D--C-EAh-C-DAj-BcIG-----Ab--Bb-BjAcAfAf--D----AeAb-ABeBaBbBc-Ag--GBFAi-Bj-EBd-AgBc-Ba-Ae----G----Aj---Bb---C---G-BdAcE-F--Aj--Ab--AgD-AgGBb----AaEAjAf-CBcBd---Ba---------AdBc-H-Ah--Aj---Ac---Ae--CAaBeG-AjAbDC--IA----BaAg--BdBb--H-Ac---Ai---Ae--Aa------Bc-H---CA-I----F--AhAi-Ac-Ag-DBaC-BdE--BcC-A--AaBb--HGAhAe-----Be-Af--F---AfAcF---Ag--BdAbC---Bj-IAeBeAdGAh",
    solution = "AjAgGHBbAdBBjBdCBaIAAiBcAbBeAaAfEAcAeDAhFBcBdBaAAhAiAjAgAbIBeAcAaAfEAeBjGFDBCBbHAdAiAcFAeAfHGDBeBaAdAbAgBbCBdBIAhBcAAjAaEBjIBjBEAbAhAfABcAaFDHAeAjAiCBbAcAdAgBaBdBeGAaDCBeAdEAeAcBbFAhBjGBdBHBaAjAAgAbAfIBcAiAcCAaBjIGAAfAdEDHBcAjBeBaBbBdAbBFAhAiAgAeDBHFBdBcIAhAcBbEBaAdAGBeAeAiAgCAaBjAfAbAjEAeAdBbAjAbAgHFBeBjBAhAcAiAfBcDAaACIGBdBaAhAfBcGAgBdBaBAaAiCAeFAbIEAdAcAjBjHBbBeADBeAiAbBaAAjBjCDAeBbAgBdAaAfFGHIAhBcAcEAdBABeAgAjGFAaIBjAdAbAfBbHAhCAcBDBaEAiBcAeBdAdBbDBcAiAeAhBaCAfBdFEBAcBjAgAHBeIGAbAjAaFAaBdAfAcBbAbAiBGBcCDBjAeIEAhAdAjBeHABaAgAeAbBjIBaAcBdEHAgAjAaAiBeABbAfFBcGAdDAhBCHEAhBCBeDAjABcIGBaAgAdAaBdAbAeAiBbFBjAcAfAfAjAcDAaICAdAeAbHABeBaBbBcAhAgBjBdGBFAiEBjHEBdBeAgBcFBaDAeAdBAhAbGICAiAaAjAAcAfBbBaAICAeBBeGAiBdAcEBjFAaAdAjAfBbAbAhBcAgDHAgGBbAhBAAcAaEAjAfAiCBcBdDHBeBaFBjAdAeIAbAbFAiAdBcAfHBbAhBjAgAjIGDAcAEBAeBaBdCAaBeGAhAjAbDCAdBeIABBcAfEBaAgFAeBdBbAiAaHBjAcBdAdBeAiBjBaEAeAfAcAaBbAjIFAhAbBcGHDAgBCABIAeAaHBjFAbGAhAiBeAcAdAgADBaCAfBdEAjBbBcCBcAAgEAaBbBdAjHGAhAeDBjBAiAdBeAcAfAbBaFIBbBaAfAcFDAiBcAgBABdAbCHAjAaBjEIAeBeAdGAh",
    difficulty = Difficulty.MEDIUM,
    sudokuType = SudokuType.Sudoku25x25,
  ),
  // Remaining Digits 346
  SudokuBlueprint(
    puzzle = "--Ad--BjBb--Bc--AF--AhAcAi-I-Bd--Ba--Aj-----Be--------AgAd-AbAcB---Be---Ai---AbBbBc---BdI-E-Ah--AdBb--BdH-Aa--A--IAeB-C--Aj-AiBe-AfAi-AfF-I--G-AgCAdAcBa-A--Aa-Bc--H-Bj--BdAf--Ba--DB-Ad-AaAe-----IFEBb--Ag---AhBj---AbF--HD-Ba-Aa-AjBAf-H-----Bb-I--Ae------BdBjAi-FC--Ad--Ab-AgAfAaBb---Ai-Bc-Be-Ae---Ba-AhAb-Ae-ACG-AiBjAj--Bb-AcEBBc--IBc-Ad-A----B-------Ba-H-Aj--Ab-BBj-C------Ba-AgAi--BdGAf-------AeBbG-AfAdH--Ab--AhAjB-AAaAc-------AcAeAj--AaI-Af------Ab-CAd-Bc--G-C-Ab-------Be----Bc-Bb-AhB--HBaAAa-Bd--AhBcD-BbAfAc-B-AeG-Ad---Aa-Bj-Ba-D---AeCAbAd-Ai--A--BbAc-AjAbBb------H--Af-C-----I-FBjAh-Ac-I-FBe--BjAg---GH---Bc--DAaAfAd-----AeBc-Aj-EG--Ag--BaC--Bd-Aj--D-Ac--Be-AdGAgEBd-Bb--H-AeAb-BaG-BdAc-B--Ae-BaAbAh--Aj--Af-BbA--CBj--Bc-Ah-BbF---HAaAc---Ab---Ag---AhBbAg-AdI--------Bc-----E--Ai--A-Ae-BdGBj--AjBe--Ac--EC--F--",
    solution = "AeDAdCEBjBbBaAjBcBeAfAFHAbAhAcAiBIAgBdAaGBaAIAjBcFAhCEBeAaAiBjBdGHAeAfAgAdDAbAcBBbAgGBeAaBHAiAcAfAeAbBbBcAjDBaBdIFEBjAhCAAdBbAcBjBdHAbAaAdAgAEAhIAeBDCBcGAjFAiBeBaAfAiAbAfFAhIDBGBdAgCAdAcBaBeABjBbAaAjBcEAeHAcBjAiBeBdAfEAjBaGBcDBAhAdAAaAeCAgAbHBbIFEBbAeAAgAiBBcAhBjBdBeAcAbFIGHDAfBaAdAaCAjBAfBcHGDAdFAaBbAICAgAeEAbBaAjBeAcBdBjAiAhFCAjIAdBdAcAbHAgAfAaBbBaEBAiAhBcBjBeDAeGAAaBaDAhAbBeAeIACGHAiBjAjBdFBbAdAcEBBcAfAgIBcAhAdBeAAgDAiAcBEGBbAaFAfCBaAeHBjAjAbBdAbHBBjAjCBeAhBbIAcAdBaBcAgAiDAaBdGAfFAEAeBdEAgAeBbGBcAfAdHCFAbAiBjAhAjBIAAaAcBaBeDDFBaAiAcAeAjBjBAaIBdAfAAhBbBeEHAbGCAdAgBcAAaGAfCBaAbEBdFDAeAjHBeAgBjAdAcBcAiBbIAhBCAgHBaAAaBjBdIAbAhBcDBeBbAfAcAjBFAeGAiAdEBcBdAaGBjAgBaHDAfFBAeCAbAdEAiBeIAAjAhBbAcBeAjAbBbDEGAiAcAdHBaAaAfACBcBdAeAhAgIBFBjAhAeAcEIAjFBeCBBjAgBdAdAiGHAbABbBcBaAfDAaAfAdFBAiBbAAeBcAhAjAcEGIAaAgDBjBaCBeHBdAbAjICDFAcAfAaBeAiAdGAgEBdBjBbAAhHBAeAbBcBaGBeBdAcAaBHAgAeEBaAbAhIBcAjAdFAfAiBbADBjCBjBEBcBaAhCBbFDAiAHAaAcAeIGAbBdAdAfAgAjBeHAhBbAgAfAdIAAbAjAeBjFBCBcBaBeAaDBdEGAcAiAdAiAAbAeBcBdGBjBaBbAjBeDAfAcBAgECAhAaFHI",
    difficulty = Difficulty.MEDIUM,
    sudokuType = SudokuType.Sudoku25x25,
  ),
)
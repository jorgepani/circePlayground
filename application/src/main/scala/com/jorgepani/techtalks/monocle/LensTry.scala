package com.jorgepani.techtalks.monocle

import monocle.Lens
import monocle.macros.GenLens

/*
A Lens is an Iso loosing information. It looses context when getting data
isoToLens[S, A](iso: Iso[S, A]): Lens[S, A] =>
  Lens(
    get = iso.get,
    set = (a, _) => iso.reverseGet(a)
  )

  the get obtains data
  set gives new data
  modify is setting with a function
 */

case class Town(name: String, province: Province, population: Int)
case class Province(name: String, region: Region)
case class Region(name: String, countryName: String)

object GeoLenses {

  val provinceLens =
    Lens.apply[Town, Province](town => town.province)(newProvince =>
      town => town.copy(province = newProvince))
  val regionLens =
    Lens.apply[Province, Region](province => province.region)(newRegion =>
      province => province.copy(region = newRegion))
  val townNameLens = Lens.apply[Town, String](town => town.name)(newName =>
    town => town.copy(name = newName))
  val regionNameLens =
    Lens.apply[Region, String](region => region.name)(newName =>
      region => region.copy(name = newName))

  // macros to not write all that boilerplate upp there
  val macroLens = GenLens[Town](_.name)
}

object LensTry extends App {

  import GeoLenses._

  def nonsenseNameExample() = {
    val riyion = Region("Murcia", "Piruletalandia")

    println(s"Region's name: ${regionNameLens.get(riyion)}")
    println(regionNameLens.set("Juliembre")(riyion))
  }

  //Lens composition
  //typical copy example
  def upperCaseWithCopy(town: Town): Town =
    town.copy(
      province = town.province.copy(
        region = town.province.region.copy(
          name = town.province.region.name.toUpperCase
        )
      ))

  //Lens example
  def upperCaseLense(town: Town): Town = {
    val regionsLens: Lens[Town, String] =
      provinceLens.composeLens(regionLens).composeLens(regionNameLens)
    regionsLens.modify(_.toUpperCase)(town)
  }

  val alcorcon = Town(
    "Alcorbronx",
    Province("Madriz", Region("Comunidat de Madrit", "Spanien!")),
    100000)

  println(upperCaseLense(alcorcon))
  println(upperCaseWithCopy(alcorcon))

  nonsenseNameExample

}

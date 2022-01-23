package com.jorgepani.techtalks.monocle

import monocle.Lens
import monocle.macros.GenLens

/*
Una Lens es una Iso que sí pierde información, pierde el contexto al acceder al dato
isoToLens[S, A](iso: Iso[S, A]): Lens[S, A] =>
  Lens(
    get = iso.get,
    set = (a, _) => iso.reverseGet(a)
  )

  get obtiene el valor
  set da uno nuevo
  modify hace un set con una funcion
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

  // macros para no escribir toda esa parrafada
  val macroLens = GenLens[Town](_.name)
}

object LensTry extends App {

  import GeoLenses._

  def nonsenseNameExample() = {
    val riyion = Region("Murcia", "Piruletalandia")

    println(s"El nombre de la region es: ${regionNameLens.get(riyion)}")
    println(regionNameLens.set("Juliembre")(riyion))
  }

  //composición de lentes
  //ejemplo típico con copy
  def upperCaseWithCopy(town: Town): Town =
    town.copy(
      province = town.province.copy(
        region = town.province.region.copy(
          name = town.province.region.name.toUpperCase
        )
      ))

  //ejemplo con lentes
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

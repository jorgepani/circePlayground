package com.jorgepani.techtalks.monocle

import monocle.Prism

/*
GetOption
reverseGet
 */
object PrismTry extends App {

  //Una iso que puede no llegar a alcanzar un valor. Head & tail example para listas
  // Pone juntos el pattern matching y el construct


  //modelado de un ADT -> Algebraic Data Types
  sealed trait Json
  case object JNull extends Json
  case class JStr(v: String) extends Json
  case class JNum(v: Double) extends Json
  case class JObj(v: Map[String, Json]) extends Json

  case class Percent private (value: Int)

  object Percent {
    def fromInt(input: Int): Option[Percent] =
      if (input >= 0 && input <= 100) {
        Some(Percent(input))
      } else {
        None
      }
  }

  val intToPercentPrism = Prism[Int, Percent](i => Percent.fromInt(i))(_.value)

  val stringPrism = Prism[Json, String] {
    case JStr(value) => Some(value)
    case _           => None
  }(_reverseGet = str => JStr(str))

  val stringPrismVPartialHelper = Prism.partial[Json, String] {
    case JStr(v) => v
  }(JStr)

  println(stringPrism.getOption(JStr("hola")))
  println(stringPrism.reverseGet("someString"))
  println(stringPrism.getOption(JNull))

  println(stringPrism.modify(_.toUpperCase)(JStr("vamos p'arriba")))

  println(stringPrism.modify(_.toUpperCase)(JNull))

}

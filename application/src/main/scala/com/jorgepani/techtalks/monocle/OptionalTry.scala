package com.jorgepani.techtalks.monocle

import monocle.Optional

/*
Coge las funciones debiles que comparten los prism y las lens y las une en un solo cacharro
y tenemos un getOption porque puede que no alcancemos lo que queremos enfocar
pero el set, necesita de un contexto al igual que las Lens
 */

sealed trait Error

case object ErrorB extends Error
case class ErrorA(message: String, details: DetailedErrorA) extends Error
case class DetailedErrorA(detailedMessage: String)

object OptionalTry extends App {

  val head = Optional[List[Int], Int] {
    case Nil     => None
    case x :: xs => Some(x)
  } { a =>
    {
      case Nil     => Nil
      case x :: xs => a :: xs
    }
  }

  val xs = List(1, 2, 3)
  val ys = List.empty[Int]

  println(head.isEmpty(xs))
  println(head.isEmpty(ys))

  println(head.set(5)(List(4, 5, 6)))

  println(head.getOption(xs))
  println(head.set(5))

}

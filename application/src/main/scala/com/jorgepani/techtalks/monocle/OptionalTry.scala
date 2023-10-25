package com.jorgepani.techtalks.monocle

import monocle.Optional

/*
It gets the weak functions shared by prisms and lens and joins them in a single artifact
giving us `getOption` because we could not reach what we are trying to focus
Set needs a context as any other Lens
 */

sealed trait Error

case object ErrorB extends Error
case class ErrorA(message: String, details: DetailedErrorA) extends Error
case class DetailedErrorA(detailedMessage: String)

object OptionalTry extends App {

  val headOptional = Optional[List[Int], Int] {
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

  println(headOptional.isEmpty(xs))
  println(headOptional.isEmpty(ys))

  println(headOptional.set(5)(List(4, 5, 6)))

  println(headOptional.getOption(xs))
  println(headOptional.set(5))

}

package com.jorgepani.techtalks.monocle

class DynamicToy extends Dynamic {
  def selectDynamic(name: String) = name

  def applyDynamic(name: String)(args: Any*) =
    s"method '$name' called with arguments ${args.mkString("'", "', '", "'")}"

  def applyDynamicNamed(name: String)(args: (String, Any)*) =
    s"method '$name' called with arguments ${args.mkString("'", "', '", "'")}"
}

class MapDynamic extends Dynamic {

  var map = Map.empty[String, Any]

  def selectDynamic(name: String) =
    map get name getOrElse println("method not found")

  def updateDynamic(name: String)(value: Any): Unit = {
    map += name -> value
  }
}

object DynamicGames extends App {

  val pera = new DynamicToy
  println(pera.limonera)

  val mapDynamic = new MapDynamic
  println(mapDynamic.foo)

  mapDynamic.foo = 10

  println(mapDynamic.foo)

}

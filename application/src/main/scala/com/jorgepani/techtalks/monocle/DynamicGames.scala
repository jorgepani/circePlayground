package com.jorgepani.techtalks.monocle

import org.slf4j.LoggerFactory

class DynamicToy extends Dynamic {
  def selectDynamic(name: String) = name

  def applyDynamic(name: String)(args: Any*) =
    s"method '$name' called with arguments ${args.mkString("'", "', '", "'")}"

  def applyDynamicNamed(name: String)(args: (String, Any)*) =
    s"method '$name' called with arguments ${args.mkString("'", "', '", "'")}"
}

class MapDynamic extends Dynamic {

  var map = collection.mutable.Map.empty[String, String]

  def selectDynamic(name: String) =
    map get name getOrElse "method not found"

  def updateDynamic(name: String)(value: String): Unit = {
    map(name) = value
  }
}

object DynamicGames extends App {

  private val log = LoggerFactory.getLogger(this.getClass)

  val pera = new DynamicToy
  log.info(pera.limonera)

  val mapDynamic = new MapDynamic

  log.info(mapDynamic.foo)
  mapDynamic.foo = "10"

  log.info(mapDynamic.foo)

}

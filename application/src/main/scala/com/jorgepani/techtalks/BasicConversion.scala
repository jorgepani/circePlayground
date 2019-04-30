package com.jorgepani.techtalks

import org.slf4j.LoggerFactory
import io.circe.Json

object BasicConversion extends App {
  private val log = LoggerFactory.getLogger(this.getClass)

  val jsonFromBoolean = Json.fromBoolean(true)
  val jsonFromInt = Json.fromInt(1)
  val jsonFromString = Json.fromString("value1")

  val fieldList = List(("key1", jsonFromString),
                       ("key2", jsonFromInt),
                       ("key3", jsonFromBoolean))

  val jsonFromFields = Json.fromFields(fieldList)

  log.info(s"""Parsed no spaces: ${jsonFromFields.noSpaces}""");
  log.info(s"""Parsed spaces 2 : ${jsonFromFields.spaces2}""");
  log.info(s"""Parsed spaces 4 : ${jsonFromFields.spaces4}""");

  val jsonFromList =
    Json.fromValues(List(Json.fromString("hola"), Json.fromString("que tal")))
  log.info(s"""Parsed list of elements: ${jsonFromList.noSpaces}""")
}

package com.jorgepani.techtalks

import io.circe.{HCursor, Json}
import io.circe.parser._
import org.slf4j.LoggerFactory

object TraversingJson extends App {

  private val log = LoggerFactory.getLogger(this.getClass)

  val jsonString =
    """
      |{
      |    "name" : "Starlord",
      |    "age" : 33,
      |    "mad" : true,
      |    "favNumbers" : [
      |        3,
      |        5,
      |        8
      |    ],
      |    "stats" : {
      |        "hair": "blonde",
      |        "legs": 2,
      |        "weapons": "guns"
      |    }
      |}
    """.stripMargin

  val doc: Json = parse(jsonString).getOrElse(Json.Null)

  log.info(s"""Parsed document: ${doc.spaces4}""")

  //Historic Cursor
  val cursor: HCursor = doc.hcursor

  val weaponCursor = cursor.downField("stats").downField("weapons")

  val weapons: String = weaponCursor.as[String] match {
    case Right(something) ⇒ something
    case _ ⇒ ""
  }

  log.info(s"The weapon is: $weapons")

  weaponCursor.withFocus(_.mapString(_ ⇒ "armagedon!"))

}

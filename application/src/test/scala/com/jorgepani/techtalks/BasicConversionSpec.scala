package com.jorgepani.techtalks

import io.circe.Json
import org.scalatest.{MustMatchers, WordSpec}

/* We want this Json
{
    "name" : "Starlord",
    "age" : 33,
    "mad" : true,
    "favNumbers" : [
        3,
        5,
        8
    ]
}
 */

class BasicConversionSpec extends WordSpec with MustMatchers {
  "Circeplayground" should {
    "convert an object into Json" in {

      val numbers = List[Int](3, 5, 8).map(Json.fromInt)

      val fieldList = List(
        ("name", Json.fromString("Starlord")),
        ("age", Json.fromInt(33)),
        ("mad", Json.fromBoolean(true)),
        ("favNumbers", Json.fromValues(numbers))
      )

      val jsonFromFields = Json.fromFields(fieldList)

      jsonFromFields.noSpaces must be(
        "{\"name\":\"Starlord\",\"age\":33,\"mad\":true,\"favNumbers\":[3,5,8]}")

    }
  }
}

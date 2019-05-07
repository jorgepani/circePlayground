package com.jorgepani.techtalks

import com.jorgepani.techtalks.anvilOfModels.Jsones
import io.circe.Json
import io.circe.optics.JsonPath.root
import io.circe.parser.parse
import org.scalatest.{MustMatchers, WordSpec}

class CirceSelectorsSpec extends WordSpec with MustMatchers {
  "Circeplayground" should {
    "traverse a Json to modify it with Optics" in {
      //All parameters should be required. Use
      val jsonString = Jsones.swaggerJson

      val jsonParsed = parse(jsonString).getOrElse(Json.Null)

      //Just to compile
      println(jsonString)

      val alteredResponse =
        root.paths.data.get.parameters.each.required.boolean.modify(_ ⇒
          true)

      val alteredJson: Json = alteredResponse(jsonParsed)

      parse(Jsones.swaggerJsonModified()).getOrElse(Json.Null).noSpaces must be(
        alteredJson.noSpaces)

    }
  }
}

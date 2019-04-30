package com.jorgepani.techtalks

import java.time.LocalDate

import com.jorgepani.techtalks.anvilOfModels.{
  Decoders,
  Encoders,
  HeroGang,
  Power,
  SuperHero
}
import io.circe.Json
import io.circe.syntax._
import io.circe.parser._
import org.scalatest.{MustMatchers, WordSpec}

class EncodingAndDecodingSpec
    extends WordSpec
    with MustMatchers
    with Encoders
    with Decoders {

  "Circeplayground" should {
    "convert an object into Json" in {

      val ironManPowers = List[Power](
        Power("Rich", "Have all money he wants to buy spare parts"),
        Power("SuperIntelligence",
              "The smartest one when thinking about destroying things"),
        Power("SuperBeard", "The best beard of all heroes")
      )

      val blackPantherPowers = List[Power](
        Power("Rich", "Wakanda forever"),
        Power("Black", "That's a superpower sometimes"),
        Power("King in the south",
              "The king of somewhere in africa with a huge air conditioner")
      )

      val ironMan = SuperHero("IronMan", "Male", ironManPowers, LocalDate.now)
      val blackPanther =
        SuperHero("BlackPanther", "male", blackPantherPowers, LocalDate.now)

      val avengers =
        HeroGang("Avengers", List[SuperHero](ironMan, blackPanther))

      val json = avengers.asJson

      parse(json.noSpaces).getOrElse(Json.Null).as[HeroGang].right.get must be(
        avengers)

    }
  }

}

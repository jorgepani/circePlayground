package com.jorgepani.techtalks.anvilOfModels

import java.time.LocalDate

import io.circe.{Decoder, Encoder, HCursor}
import io.circe.syntax._

trait Encoders {
  implicit val heroGangEncoder: Encoder[HeroGang] =
    Encoder.forProduct2("name", "heroes")(
      element ⇒ (element.name.asJson, element.heroes.asJson)
    )

  implicit val superHeroEncoder: Encoder[SuperHero] =
    Encoder.forProduct3("name", "gender", "powers")(
      hero ⇒ (hero.name.asJson, hero.gender.asJson, hero.powers.asJson)
    )

  implicit val powerEncoder: Encoder[Power] =
    Encoder.forProduct2("name", "description")(
      power ⇒ (power.name.asJson, power.description.asJson)
    )
}

trait Decoders {
  implicit val powerDecoder: Decoder[Power] = (c: HCursor) =>
    for {
      foo <- c.downField("name").as[String]
      bar <- c.downField("description").as[String]
    } yield Power(foo, bar)

  implicit val superHeroDecoder: Decoder[SuperHero] = new Decoder[SuperHero] {
    final def apply(c: HCursor): Decoder.Result[SuperHero] =
      for {
        name <- c.downField("name").as[String]
        gender <- c.downField("gender").as[String]
        powers ← c.downField("powers").as[List[Power]]
      } yield SuperHero(name, gender, powers, LocalDate.now)

  }

  implicit val heroGangDecoder: Decoder[HeroGang] = new Decoder[HeroGang] {
    final def apply(c: HCursor): Decoder.Result[HeroGang] =
      for {
        name <- c.downField("name").as[String]
        heroes <- c.downField("heroes").as[List[SuperHero]]

      } yield HeroGang(name, heroes)
  }
}

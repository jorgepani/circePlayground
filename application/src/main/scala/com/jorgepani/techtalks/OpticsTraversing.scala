package com.jorgepani.techtalks

import com.jorgepani.techtalks.anvilOfModels.Jsones
import io.circe.Json
import io.circe.optics.JsonPath._
import io.circe.parser._
import org.slf4j.LoggerFactory

object OpticsTraversing extends App {

  private val log = LoggerFactory.getLogger(this.getClass)

  val swagger = Jsones.swaggerJson()
  val jsonParsed = parse(swagger).getOrElse(Json.Null)

  //Create an optic
  val descriptionOptic = root.paths.data.get.description.string
  //Use optic to get data
  val description = descriptionOptic.getOption(jsonParsed)

  description.map(log.info)

  //play with arrays
  val parameterNames: Seq[String] =
    root.paths.data.get.parameters.each.name.string.getAll(jsonParsed)

  parameterNames.map(parameter => log.info(s"""Parameter found "$parameter""""))

  val alterResponseOk: Json ⇒ Json =
    root.paths.data.get.responses.ok.status.int.modify(_ ⇒ 500)

  //Modifiying json
  val alteredJson = alterResponseOk(jsonParsed)

  log.info(alteredJson.spaces2)

}

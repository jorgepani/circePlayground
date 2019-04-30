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

  //get an element
  val _description = root.paths.seometadata.get.description.string
  val cosa = _description.getOption(jsonParsed)

  println(cosa)

  //play with arrays
  val parameterNames: Seq[String] =
    root.paths.seometadata.get.parameters.each.name.string.getAll(jsonParsed)

  val alterResponseOk: Json ⇒ Json =
    root.paths.seometadata.get.responses.ok.status.int.modify(_ ⇒ 500)

  //Modifiying json
  val alteredJson = alterResponseOk(jsonParsed)

  log.info(alteredJson.spaces2)

}

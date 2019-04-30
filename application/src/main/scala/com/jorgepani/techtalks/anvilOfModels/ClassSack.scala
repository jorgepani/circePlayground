package com.jorgepani.techtalks.anvilOfModels

import java.time.LocalDate

case class Power(name: String, description: String)
case class SuperHero(name: String,
                     gender: String,
                     powers: List[Power],
                     birthdate: LocalDate)
case class HeroGang(name: String, heroes: List[SuperHero])

class BujeroDeClases {}

object Jsones {
  def swaggerJson(): String =
    s"""{
        "paths": {
          "seometadata": {
            "get": {
              "summary": "Listing",
              "description": "Returns the seo status for a destination.",
              "parameters": [
                {
                  "name": "lbsId",
                  "in": "path",
                  "description": "Listing unique identifier.",
                  "required": true,
                  "type": "string"
                },
                {
                  "name": "site",
                  "in": "path",
                  "description": "site",
                  "required": true,
                  "type": "string"
                },
                {
                  "name": "refinementKey",
                  "in": "query",
                  "description": "Refinement Key that the returned listings should be part of",
                  "required": false,
                  "type": "string"
                }
              ],
              "tags": [
                "Querying"
              ],
              "responses": {
                "ok": {
                  "status": 200,
                  "description": "successful operation",
                  "schema": {
                    "reference": "#/definitions/SeoMetadataResponse"
                  }
                }
              }
            }
          }
        } 
      }"""

  def swaggerJsonModified(): String =
    s"""{
        "paths": {
          "seometadata": {
            "get": {
              "summary": "Listing",
              "description": "Returns the seo status for a destination.",
              "parameters": [
                {
                  "name": "lbsId",
                  "in": "path",
                  "description": "Listing unique identifier.",
                  "required": true,
                  "type": "string"
                },
                {
                  "name": "site",
                  "in": "path",
                  "description": "site",
                  "required": true,
                  "type": "string"
                },
                {
                  "name": "refinementKey",
                  "in": "query",
                  "description": "Refinement Key that the returned listings should be part of",
                  "required": true,
                  "type": "string"
                }
              ],
              "tags": [
                "Querying"
              ],
              "responses": {
                "ok": {
                  "status": 200,
                  "description": "successful operation",
                  "schema": {
                    "reference": "#/definitions/SeoMetadataResponse"
                  }
                }
              }
            }
          }
        } 
      }"""
}

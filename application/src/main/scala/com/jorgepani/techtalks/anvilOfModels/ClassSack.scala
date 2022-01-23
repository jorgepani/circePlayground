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
          "data": {
            "get": {
              "summary": "Listing",
              "description": "Returns the data status for a place.",
              "parameters": [
                {
                  "name": "placeId",
                  "in": "path",
                  "description": "unique identifier.",
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
                  "name": "filterKey",
                  "in": "query",
                  "description": "filter key",
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
                    "reference": "#/definitions/PathDataResponse"
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
          "data": {
            "get": {
              "summary": "Listing",
              "description": "Returns the data status for a place.",
              "parameters": [
                {
                  "name": "placeId",
                  "in": "path",
                  "description": "unique identifier.",
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
                  "name": "filterKey",
                  "in": "query",
                  "description": "filter key",
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
                    "reference": "#/definitions/PathDataResponse"
                  }
                }
              }
            }
          }
        } 
      }"""
}

package com.jorgepani.techtalks

import io.circe.{HCursor, Json}
import io.circe.parser._
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TraversingJsonSpec extends AnyWordSpec with Matchers {
  "Circeplayground" should {
    "traverse a Json to get all counts" in {

      val jsonString: String = """
         {
         "id": "c730433b-082c-4984-9d66-855c243266f0",
         "name": "Foo",
         "values": {
            "bar": true,
            "baz": 100.001,
            "qux": ["a", "b"],
            "counts": [1, 2, 3]
            }
         } """

      //Historic Cursor
      val doc: Json = parse(jsonString).getOrElse(Json.Null)
      val cursor: HCursor = doc.hcursor

      //val counts: Either[DecodingFailure, Int] =
      //cursor.downField("values").downField("counts").as[List[Int]].map(_.sum)

      //Just to avoid a compilation error
      print(cursor)

      //counts.right must be(Some(6))

    }
  }
}

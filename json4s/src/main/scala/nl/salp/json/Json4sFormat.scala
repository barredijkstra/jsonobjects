package nl.salp.json

import org.json4s.{JValue, JsonMethods}

class Json4sFormat[ParsedType](jsonMethod: JsonMethods[ParsedType]) extends JsonFormat[JValue] {

  override def readJson(input: String): JValue =
    jsonMethod.parse(input)

  override def asJsonString(json: JValue): String =
    jsonMethod.compact(jsonMethod.render(json))

}

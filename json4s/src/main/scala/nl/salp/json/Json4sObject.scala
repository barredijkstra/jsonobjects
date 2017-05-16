package nl.salp.json

import nl.salp.json
import org.json4s.{Formats, JValue}

class Json4sObject[ObjectType: Manifest](value: ObjectType)(
  implicit
  jsonFmt: JsonFormat[JValue],
  jsonObjFmtCreator: JsonObjectFormat[ObjectType, JValue],
  fmt: Formats
)
  extends JsonObject[ObjectType, JValue](value) {
}

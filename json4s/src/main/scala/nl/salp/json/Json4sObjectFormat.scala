package nl.salp.json

import org.json4s.{Extraction, Formats, JValue}

class Json4sObjectFormat[ObjectType: Manifest](implicit fmt: Formats) extends JsonObjectFormat[ObjectType, JValue] {
  override def parseJson(json: JValue): ObjectType =
    json.extract[ObjectType]

  override def parseObject(instance: ObjectType): JValue =
    Extraction.decompose(instance)

}

package nl.salp.json

abstract class JsonObject[ObjectType: Manifest, JsonType](value: ObjectType)(implicit jsonFmt: JsonFormat[JsonType], objFmt: JsonObjectFormat[ObjectType, JsonType]) {
  def readFrom(input: String): ObjectType =
    jsonFmt.readObject(input)

  def fromJson(json: JsonType): ObjectType =
    jsonFmt.parseJson(json)

  def asJson: JsonType =
    jsonFmt.parseObject(value)

  def asJsonString: String =
    jsonFmt.asJsonString(value)
}

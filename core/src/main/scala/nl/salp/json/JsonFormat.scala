package nl.salp.json

trait JsonFormat[JsonType] {
  def readJson(input: String): JsonType

  def asJsonString(json: JsonType): String

  def parseJson[ObjectType: Manifest](json: JsonType)(implicit fmt: JsonObjectFormat[ObjectType, JsonType]): ObjectType =
    fmt.parseJson(json)

  def parseObject[ObjectType: Manifest](instance: ObjectType)(implicit fmt: JsonObjectFormat[ObjectType, JsonType]): JsonType =
    fmt.parseObject(instance)

  def readObject[ObjectType: Manifest](input: String)(implicit fmt: JsonObjectFormat[ObjectType, JsonType]): ObjectType =
    parseJson(readJson(input))

  def asJsonString[ObjectType: Manifest](instance: ObjectType)(implicit fmt: JsonObjectFormat[ObjectType, JsonType]): String =
    asJsonString(parseObject(instance))
}

trait JsonFormatImplicit[JsonType] {
  implicit val jsonFormat: JsonFormat[JsonType]
  implicit def object2JsonObjectFormat[ObjectType: Manifest]: JsonObjectFormat[ObjectType, JsonType]
}

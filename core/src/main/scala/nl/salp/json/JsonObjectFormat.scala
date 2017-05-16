package nl.salp.json

trait JsonObjectFormat[ObjectType, JsonType] {
  def parseJson(json: JsonType): ObjectType

  def parseObject(instance: ObjectType): JsonType
}

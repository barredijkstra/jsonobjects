/*
 * Copyright 2017 Barre Dijkstra
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

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

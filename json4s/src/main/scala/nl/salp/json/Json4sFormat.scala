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

import org.json4s.{JValue, JsonMethods}

class Json4sFormat[ParsedType](jsonMethod: JsonMethods[ParsedType]) extends JsonFormat[JValue] {

  override def readJson(input: String): JValue =
    jsonMethod.parse(input)

  override def asJsonString(json: JValue): String =
    jsonMethod.compact(jsonMethod.render(json))

}

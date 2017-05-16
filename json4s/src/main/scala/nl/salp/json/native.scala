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

import org.json4s.{native => j4sn, _}

object native extends JsonFormatImplicit[JValue] {

  implicit val defaultJson4sFormats: Formats = DefaultFormats

  implicit val jsonFormat = new Json4sFormat(j4sn.JsonMethods)

  implicit def object2JsonObjectFormat[ObjectType: Manifest]: JsonObjectFormat[ObjectType, JValue] = {
    new Json4sObjectFormat[ObjectType]
  }
}

package nl.salp.json

import org.json4s.ext.JodaTimeSerializers
import org.json4s.{native => j4sn, _}

object native extends JsonFormatImplicit[JValue] {

  implicit val defaultJson4sFormats: Formats = DefaultFormats ++ JodaTimeSerializers.all

  implicit val jsonFormat = new Json4sFormat(j4sn.JsonMethods)

  implicit def object2JsonObjectFormat[ObjectType: Manifest]: JsonObjectFormat[ObjectType, JValue] = {
    new Json4sObjectFormat[ObjectType]
  }
}

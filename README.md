# jsonobjects
Simple project that allows for easy extension with Json functionality of (existing) case classes without adjusting the case classes themselves.

This project allows for implicit extension of case classes with default or custom JSON marshalling/unmarshalling while supporting multiple JSON frameworks. 

## Why
Mostly because I dislike adding things like JSON functionality to the actual implementation of case classes as well as writing wrappers.
 
## Current state
This project is heavy work in-progress, don't count on it fully working or the contracts being fixed.

## Usage

For implicit addition without any fuzz:

    case class MyClass(field1: Int, field2: String)
    object MyClass {
      import nl.salp.json.native._
      
      implicit class JsonMyClass(value: MyClass) extends Json4sObject[MyClass](value)
      
      def apply(json: String): MyClass = jsonFormat.readObject[MyClass](json)
    }
    
    class OtherClass {
      def printJson(instance: MyClass): Unit =
        println(instance.asJsonString)
      
      def readJson(json: String): MyClass =
        MyClass(json)
    }

More complex marshalling/unmarshalling can be achieved by pulling your own JsonObjectFormat into the scope

    case class MyClass(field1: Int, field2: String)
    object MyClass {
      import nl.salp.json.native._
      
      implicit object MyClassObjectFormat extends Json4sObjectFormat[MyClass] {
        override def parseJson(json: JValue): MyClass = {
          // Use any preferred method of parsing like for-comprehensions here.
          val field1 = (json \ "field1").extract[Int] * 2
          val field2 = (json \ "field2").extract[String]
          MyClass(field1, field2)
        }
        
        override def parseObject(obj: MyClass): JValue = {
          // Use any preferred method of creating Json objects here
          import org.json4s.JsonDSL._
          ("field1" -> obj.field1 / 2) ~ ("field2" -> obj.field2)
        }
      }
        
      implicit class JsonMyClass(value: MyClass) extends Json4sObject[MyClass](value)
      
      def apply(json: String): MyClass = jsonFormat.readObject[MyClass](json)
    }
    
    class OtherClass {
      def printJson(instance: MyClass): Unit =
        println(instance.asJsonString)
      
      def readJson(json: String): MyClass =
        MyClass(json)
    }

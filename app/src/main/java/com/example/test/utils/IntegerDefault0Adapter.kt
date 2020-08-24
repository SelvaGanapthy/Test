package com.example.test.utils

import com.google.gson.*
import java.lang.reflect.Type

class IntegerDefault0Adapter : JsonSerializer<Integer>, JsonDeserializer<Integer> {
    override fun serialize(
        src: Integer?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Integer? {
        try {
            if (json.asString == "" || json.asString == "null") {// Defined as int type , If the background returns "" perhaps null, Then return 0
                return Integer(0)
            }
        } catch (ignore: Exception) {
        }

        try {
            return Integer(json.asInt)
        } catch (e: NumberFormatException) {
            throw JsonSyntaxException(e)
        }

    }


}
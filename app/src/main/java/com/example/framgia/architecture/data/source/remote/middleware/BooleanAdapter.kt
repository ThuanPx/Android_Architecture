package com.example.framgia.architecture.data.source.remote.middleware

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException

class BooleanAdapter : TypeAdapter<Boolean>() {
    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: Boolean?) {
        if (value == null) {
            out.nullValue()
            return
        }
        out.value(value)
    }

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): Boolean? {
        val peek = `in`.peek()
        return when (peek) {
            JsonToken.NULL -> {
                `in`.nextNull()
                null
            }
            JsonToken.BOOLEAN -> `in`.nextBoolean()
            JsonToken.NUMBER -> `in`.nextInt() != 0
            JsonToken.STRING -> java.lang.Boolean.valueOf(`in`.nextString())
            else -> null
        }
    }
}

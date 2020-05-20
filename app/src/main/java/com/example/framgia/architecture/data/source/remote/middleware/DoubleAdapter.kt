package com.example.framgia.architecture.data.source.remote.middleware

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException

class DoubleAdapter : TypeAdapter<Double>() {

    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: Double?) {
        if (value == null) {
            out.nullValue()
            return
        }
        out.value(value)
    }

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): Double? {
        return when (`in`.peek()) {
            JsonToken.NULL -> {
                `in`.nextNull()
                null
            }
            JsonToken.NUMBER -> `in`.nextDouble()
            JsonToken.STRING -> {
                try {
                    java.lang.Double.valueOf(`in`.nextString())
                } catch (e: NumberFormatException) {
                    null
                }
            }
            else -> null
        }
    }
}

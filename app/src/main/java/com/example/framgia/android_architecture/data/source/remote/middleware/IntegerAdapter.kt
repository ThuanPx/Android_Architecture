package com.example.framgia.android_architecture.data.source.remote.middleware

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException

class IntegerAdapter : TypeAdapter<Int>() {
  @Throws(IOException::class)
  override fun write(out: JsonWriter, value: Int?) {
    if (value == null) {
      out.nullValue()
      return
    }
    out.value(value)
  }

  @Throws(IOException::class)
  override fun read(`in`: JsonReader): Int? {
    val peek = `in`.peek()
    when (peek) {
      JsonToken.NULL -> {
        `in`.nextNull()
        return null
      }
      JsonToken.NUMBER -> return `in`.nextInt()
      JsonToken.BOOLEAN -> return if (`in`.nextBoolean()) 1 else 0
      JsonToken.STRING -> {
        return try {
          Integer.valueOf(`in`.nextString())
        } catch (e: NumberFormatException) {
          null
        }
      }
      else -> return null
    }
  }
}
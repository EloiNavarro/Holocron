package com.eloinavarro.holocron.data.room.converters

import androidx.room.TypeConverter
import com.eloinavarro.holocron.domain.SwLink
import com.eloinavarro.holocron.domain.SwLinkList
import com.eloinavarro.holocron.domain.SwLinkType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class SWTypeConverters {
    private val gson = Gson()

    // Converters for SwLinkType
    @TypeConverter
    fun fromSwLinkType(value: SwLinkType): String {
        return value.name
    }

    @TypeConverter
    fun toSwLinkType(value: String): SwLinkType {
        return SwLinkType.valueOf(value)
    }

    // Converters for SwLinkList
    @TypeConverter
    fun fromSwLinkList(value: SwLinkList): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toSwLinkList(value: String): SwLinkList {
        val type = object : TypeToken<SwLinkList>() {}.type
        return gson.fromJson(value, type)
    }

    // Converters for List<SwLinkList>
    @TypeConverter
    fun fromSwLinkListList(value: List<SwLinkList>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toSwLinkListList(value: String): List<SwLinkList> {
        val type = object : TypeToken<List<SwLinkList>>() {}.type
        return gson.fromJson(value, type)
    }

    // Converters for SwLink
    @TypeConverter
    fun fromSwLink(value: SwLink): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toSwLink(value: String): SwLink {
        val type = object : TypeToken<SwLink>() {}.type
        return gson.fromJson(value, type)
    }

    // Converters for List<String>
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }
}
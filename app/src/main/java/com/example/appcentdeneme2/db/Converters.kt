package com.example.appcentdeneme2.db

import androidx.room.TypeConverter
import com.example.appcentdeneme2.models.Source
class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        //return source.name
        return source.name ?: ""
    }

    @TypeConverter
    fun toSource(name: String): Source{
        return Source(name, name)
    }
}
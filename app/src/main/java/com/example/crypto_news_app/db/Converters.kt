package com.example.crypto_news_app.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.crypto_news_app.models.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String{
        return source.name
    }

    fun toSource(name: String): Source {
        return Source(name, name)
    }

}
package com.example.photoslibrary.db

import androidx.room.TypeConverter
import com.example.photoslibrary.model.Urls


class Converters {

    @TypeConverter
    fun fromUrls(urls: Urls): String {
        return urls.small
    }

    @TypeConverter
    fun toUrls(small: String): Urls {
        return Urls(small)
    }
}
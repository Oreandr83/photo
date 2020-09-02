package com.example.photoslibrary.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.photoslibrary.model.Photo

@Database(
    entities = [Photo::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class PhotoDatabase : RoomDatabase() {

    abstract fun getPhotoDao(): PhotoDao

    companion object {
        @Volatile
        private var instance: PhotoDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PhotoDatabase::class.java,
                "photo_db"
            ).build()
    }

}
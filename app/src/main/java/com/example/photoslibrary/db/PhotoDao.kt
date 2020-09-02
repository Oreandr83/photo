package com.example.photoslibrary.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.photoslibrary.model.Photo


@Dao
interface PhotoDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun upsert(photo: Photo): Long

    @Query("select * from photos")
    fun getAllPhotos(): LiveData<List<Photo>>

    @Delete
    suspend fun  deletePhoto(photo: Photo)
}
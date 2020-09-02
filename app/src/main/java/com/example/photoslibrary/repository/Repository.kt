package com.example.photoslibrary.repository

import com.example.photoslibrary.api.AppClient
import com.example.photoslibrary.db.PhotoDatabase
import com.example.photoslibrary.model.Photo


class Repository(val db: PhotoDatabase)
{

  suspend fun getPhotos(apiKey: String) =
    AppClient.api.getPhotos(apiKey)


    suspend fun getPhoto(apiKey: String, id:String) =
        AppClient.api.getPhoto(apiKey, id)

    suspend fun upsert(photo: Photo) = db.getPhotoDao().upsert(photo)

    fun getSavedPhoto() = db.getPhotoDao().getAllPhotos()

    suspend fun deletePhoto(photo: Photo) = db.getPhotoDao().deletePhoto(photo)
}
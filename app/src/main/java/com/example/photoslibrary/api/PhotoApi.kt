package com.example.photoslibrary.api

import com.example.photoslibrary.model.Photo
import com.example.photoslibrary.model.Photos
import com.example.photoslibrary.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotoApi {

    @GET("photos")
    suspend fun getPhotos(

        @Query("client_id")
        client_id: String = API_KEY

    ):Response<Photos>


    @GET("photos/{id}")
    suspend fun getPhoto(

        @Query("client_id")
        apiKey: String = API_KEY,

        @Path("id")
        id: String

    ):Response<Photo>

}


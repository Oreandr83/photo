package com.example.photoslibrary.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(
    tableName = "photos"
)
data class Photo(

    @PrimaryKey(autoGenerate = true)
    var field: Int? = null,

    @SerializedName("id")
    @Expose
    val id: String,

    val description: String?,

    val urls: Urls

): Serializable
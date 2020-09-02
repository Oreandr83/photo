package com.example.photoslibrary.arch_skeleton
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photoslibrary.model.Photo
import com.example.photoslibrary.model.Photos
import com.example.photoslibrary.repository.Repository
import com.example.photoslibrary.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response


class PhotoViewModel(val photoRepository: Repository): ViewModel() {

    val photos: MutableLiveData<Resource<Photos>> = MutableLiveData()

    val photo: MutableLiveData<Resource<Photo>> = MutableLiveData()

    init {
        getPhotos("hGJrVA3CDujS-q69HGSUANNPrKF_zKFHK_MDrEL0lPo")
    }

    fun getPhotos(apiKey: String) = viewModelScope.launch {
        photos.postValue(Resource.Loading())
        val response = photoRepository.getPhotos(apiKey)//,photoPage)
        photos.postValue(handlePhotosResponse(response))
    }

    fun getPhoto(apiKey: String, id:String) = viewModelScope.launch {
        photo.postValue(Resource.Loading())
        val response = photoRepository.getPhoto(apiKey, id)
        photo.postValue(handlePhotoResponse(response))
    }

   private fun handlePhotosResponse(response: Response<Photos>): Resource<Photos> {
       if(response.isSuccessful){
           response.body()?.let { resultResponse ->
               return Resource.Success(resultResponse)
           }
       }
       return Resource.Error(response.message())
   }

    private fun handlePhotoResponse(response: Response<Photo>): Resource<Photo> {
        if(response.isSuccessful){
            response.body()?.let{resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


    fun savePhoto(photo: Photo) = viewModelScope.launch {
        photoRepository.upsert(photo)
    }

    fun getSavedPhoto() = photoRepository.getSavedPhoto()

    fun deletePhoto(photo: Photo) = viewModelScope.launch {
        photoRepository.deletePhoto(photo)
    }
}
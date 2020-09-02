package com.example.photoslibrary.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.photoslibrary.arch_skeleton.PhotoViewModel


class ViewModelProviderFactory(val photoRepository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotoViewModel(photoRepository) as T

    }
}
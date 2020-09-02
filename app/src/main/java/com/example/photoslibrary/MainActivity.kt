package com.example.photoslibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.photoslibrary.arch_skeleton.PhotoViewModel
import com.example.photoslibrary.db.PhotoDatabase
import com.example.photoslibrary.repository.Repository
import com.example.photoslibrary.repository.ViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_photo.*


class PhotoActivity : AppCompatActivity() {

    lateinit var viewModel: PhotoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        //get the ViewModel
        val repository = Repository(PhotoDatabase(this))
        val viewModelProviderFactory = ViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get((PhotoViewModel::class.java))

        bottomNavigationView.setupWithNavController(photoNavHostFragment.findNavController())
    }
}
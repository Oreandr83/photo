package com.example.photoslibrary.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.photoslibrary.PhotoActivity
import com.example.photoslibrary.R
import com.example.photoslibrary.arch_skeleton.PhotoViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment(R.layout.fragment_detail) {

    lateinit var viewModel: PhotoViewModel
    val args: DetailFragmentArgs by navArgs()

    val TAG = "DetailFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as PhotoActivity).viewModel
        val photo = args.detail

        Picasso.with(context)
            .load(photo.urls.small)
            .into(image_detail)

        title_detail?.apply {
            text = photo.description
        }

       // var id: String = photo.id

        fab.setOnClickListener {

            viewModel.savePhoto(photo)
            Snackbar.make(view, "Photo saved successfully", Snackbar.LENGTH_SHORT)
                .show()


               /* viewModel.deletePhoto(photo)
                        Snackbar.make(view,"Photo deleted", Snackbar.LENGTH_SHORT)
                            .show()
*/

            }
        }

    }







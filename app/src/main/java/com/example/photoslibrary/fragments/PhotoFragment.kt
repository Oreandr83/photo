package com.example.photoslibrary.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.photoslibrary.PhotoActivity
import com.example.photoslibrary.R
import com.example.photoslibrary.adapter.PhotoAdapter
import com.example.photoslibrary.arch_skeleton.PhotoViewModel
import com.example.photoslibrary.util.Resource
import kotlinx.android.synthetic.main.fragment_photo.*


class PhotoFragment : Fragment (R.layout.fragment_photo) {

    lateinit var viewModel: PhotoViewModel
    lateinit var photoAdapter: PhotoAdapter

    val TAG = "PhotoFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as PhotoActivity).viewModel
        setupRecyclerView()

        photoAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("detail",it)
            }
            findNavController().navigate(
                R.id.action_photoFragment_to_detailFragment,
                bundle
            )
        }

        id_swipe.setOnRefreshListener {
            fetchPhoto()
        }

        fetchPhoto()
    }


    private fun fetchPhoto() {

        id_swipe.isRefreshing = true

        viewModel.photos.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                   id_swipe.isRefreshing = false
                    response.data?.let { photoResponse ->
                        photoAdapter.differ.submitList(photoResponse)

                    }
                }
                is Resource.Error -> {
                  id_swipe.isRefreshing = false
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")

                    }
                }
                is Resource.Loading -> {
                   id_swipe.isRefreshing = true
                }
            }

        })
    }


    private fun setupRecyclerView(){
        photoAdapter = PhotoAdapter()
        rvPhoto.apply {
            adapter = photoAdapter
            layoutManager = LinearLayoutManager(activity)

        }
    }
}
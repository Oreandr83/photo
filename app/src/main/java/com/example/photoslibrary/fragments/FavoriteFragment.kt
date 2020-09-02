package com.example.photoslibrary.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.photoslibrary.PhotoActivity
import com.example.photoslibrary.R
import com.example.photoslibrary.adapter.PhotoAdapter
import com.example.photoslibrary.arch_skeleton.PhotoViewModel

import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    lateinit var viewModel: PhotoViewModel
    lateinit var photoAdapter: PhotoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as PhotoActivity).viewModel
        setupRecyclerView()

        photoAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("detail",it)
            }
                findNavController().navigate(
                    R.id.action_favoriteFragment_to_detailFragment,
                    bundle
                )
        }
         //delete saved photo
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                     ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT

        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
               return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val photo = photoAdapter.differ.currentList[position]
                viewModel.deletePhoto(photo)
                Snackbar.make(view,"Successfully deleted photo",Snackbar.LENGTH_SHORT)
                    .apply {
                        setAction("Undo"){
                            viewModel.savePhoto(photo)
                        }
                            .show()
                    }
            }

        }
         ItemTouchHelper(itemTouchHelperCallback).apply {
             attachToRecyclerView(rvSavedPhoto)
         }

        //get the saved photos from db detailfragment
        viewModel.getSavedPhoto().observe(viewLifecycleOwner, Observer {photos ->
            photoAdapter.differ.submitList(photos)

        })
    }

    private fun setupRecyclerView(){
       photoAdapter = PhotoAdapter()
        rvSavedPhoto.apply {
            adapter = photoAdapter
           layoutManager = LinearLayoutManager(activity)
        }
    }
}
package com.jacque.imagegallerytest.ui.imageGallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.jacque.imagegallerytest.databinding.ImageGalleryFragmentBinding
import com.jacque.imagegallerytest.model.ImageDb

class ImageGalleryFragment : Fragment() {

    private val images = mutableListOf<ImageDb>()
    private val args: ImageGalleryFragmentArgs by navArgs()
    private lateinit var galleryAdapter: ImageGalleryAdapter
    private lateinit var binding: ImageGalleryFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        images.apply {
            clear()
            addAll(args.album.images)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ImageGalleryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
    }

    private fun setUpRecycler() {
        val gridLayoutManager = GridLayoutManager(activity, 2)
        galleryAdapter = ImageGalleryAdapter(images, ::onRowClicked)
        binding.rvImages.run {
            layoutManager = gridLayoutManager
            adapter = galleryAdapter
        }
    }

    private fun onRowClicked(imageDb: ImageDb) {
        val index = images.indexOf(imageDb)
        val action =
            ImageGalleryFragmentDirections.actionImageGalleryToZoomedImage(args.album, index)
        findNavController().navigate(action)
    }
}
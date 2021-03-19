package com.jacque.imagegallerytest.ui.zoomedImages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.jacque.imagegallerytest.databinding.ZoomedImageGalleryFragmentBinding
import com.jacque.imagegallerytest.model.ImageDb

class ZoomedImageFragment : Fragment() {

    private val images = mutableListOf<ImageDb>()
    private val args: ZoomedImageFragmentArgs by navArgs()
    private lateinit var zoomedAdapter: ZoomedImageAdapter
    private lateinit var binding: ZoomedImageGalleryFragmentBinding

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
        binding = ZoomedImageGalleryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
    }

    private fun setUpRecycler() {
        val selectedIndex = args.selectedIndex
        zoomedAdapter = ZoomedImageAdapter(images)
        binding.rvImages.run {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = zoomedAdapter
        }
        binding.rvImages.scrollToPosition(selectedIndex)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvImages)

    }
}
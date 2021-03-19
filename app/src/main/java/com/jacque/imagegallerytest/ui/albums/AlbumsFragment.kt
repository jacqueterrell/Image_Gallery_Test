package com.jacque.imagegallerytest.ui.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jacque.imagegallerytest.databinding.AlbumsListFragmentBinding
import com.jacque.imagegallerytest.model.AlbumDb
import com.jacque.imagegallerytest.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent

class AlbumsFragment : Fragment() {

    private lateinit var binding: AlbumsListFragmentBinding
    private lateinit var albumsAdapter: AlbumsAdapter
    private var viewModel = KoinJavaComponent.get(AlbumsViewModel::class.java)
    private val albumsList = mutableListOf<AlbumDb>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AlbumsListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
        setUpRecycler()
    }

    private fun setUpRecycler() {
        albumsAdapter = AlbumsAdapter(albumsList, ::onRowClicked)
        binding.rvAlbums.apply {
            adapter = albumsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setUpListeners() {
        binding.btnSearch.setOnClickListener {
            val text = binding.etSearch.text.toString()
            if (text.length < 3) {
                showToast("Please enter a search term of at least three letters")
            } else {
                hideKeyboard()
                requestAlbums(text)
            }
        }
        binding.etSearch.onSearch {
            binding.btnSearch.performClick()
        }
    }

    private fun onRowClicked(albumsDb: AlbumDb) {
        val action = AlbumsFragmentDirections.actionAlbumToImageGallery(albumsDb)
        findNavController().navigate(action)
    }

    private fun requestAlbums(text: String) {
        showProgress()
        lifecycleScope.launch(Dispatchers.IO) {
            val response = viewModel.requestAlbums(text)
            withContext(Dispatchers.Main) {
                dismissProgress()
                if (response is ApiResponse.Success) {
                    albumsList.clear()
                    albumsList.addAll(response.data)
                    albumsAdapter.notifyDataSetChanged()
                } else {
                    response as ApiResponse.Failure
                    val msg = response.errorMessage ?: "There was an error in retrieving albums"
                    showToast(msg)
                }
            }
        }
    }

    private fun showProgress() {
        binding.progressSpinner.visible()
    }

    private fun dismissProgress() {
        binding.progressSpinner.gone()
    }
}
package com.jacque.imagegallerytest.ui.albums

import androidx.lifecycle.ViewModel
import com.jacque.imagegallerytest.model.AlbumDb
import com.jacque.imagegallerytest.utils.ApiResponse

class AlbumsViewModel(private val repository: AlbumsRepository) : ViewModel() {

    /**
     * Makes a call to our [AlbumsRepository.requestAlbums] to get the list of [AlbumDb]
     */
    suspend fun requestAlbums(text: String): ApiResponse<List<AlbumDb>> {
        return try {
            val albums = repository.requestAlbums(text)
            ApiResponse.Success(albums)
        } catch (e: Exception) {
            ApiResponse.Failure(e.localizedMessage, e)
        }
    }
}
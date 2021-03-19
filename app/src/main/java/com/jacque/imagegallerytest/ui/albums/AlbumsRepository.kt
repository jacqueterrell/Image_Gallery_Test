package com.jacque.imagegallerytest.ui.albums

import com.jacque.imagegallerytest.model.AlbumDb
import com.jacque.imagegallerytest.network.ImgurApiEndpoints

class AlbumsRepository(private val apiEndpoints: ImgurApiEndpoints) {

    /**
     * Uses the [ImgurApiEndpoints.requestAlbums] to return our list of [AlbumDb]
     */
    suspend fun requestAlbums(text: String): List<AlbumDb> {
        val response = apiEndpoints.requestAlbums(text)
        return response.data
    }
}
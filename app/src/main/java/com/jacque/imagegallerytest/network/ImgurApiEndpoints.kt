package com.jacque.imagegallerytest.network

import com.jacque.imagegallerytest.model.AlbumResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ImgurApiEndpoints {

    private val clientId: String
        get() = "Client-ID 2d086962f60c89e"

    @GET("search")
    suspend fun requestAlbums(
        @Query("q_all") qAll: String,
        @Query("q_type") qType: String = "album",
        @Header("Authorization") token: String = clientId
    ): AlbumResponse
}
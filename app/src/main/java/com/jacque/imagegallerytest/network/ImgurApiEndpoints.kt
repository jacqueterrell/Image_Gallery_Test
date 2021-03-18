package com.jacque.imagegallerytest.network

import com.jacque.imagegallerytest.model.AlbumResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ImgurApiEndpoints {
    
    private val clientId: String
        get() = "Client-ID 2d086962f60c89e"

    @GET("search?/q_all={query}&q_type=album")
    suspend fun requestAlbums(
        @Path("query") query: Query,
        @Header("Authorization") token: String = clientId
    ): AlbumResponse
}
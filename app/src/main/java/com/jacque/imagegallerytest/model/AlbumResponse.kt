package com.jacque.imagegallerytest.model

data class AlbumResponse(
    val id: String = "",
    val title: String = "",
    val tags: ArrayList<AlbumsDb> = ArrayList<AlbumsDb>()
)

data class AlbumsDb(
    val name: String = "",
    val display_name: String = "",
    val followers: Int = 0,
    val total_items: Int = 0
)
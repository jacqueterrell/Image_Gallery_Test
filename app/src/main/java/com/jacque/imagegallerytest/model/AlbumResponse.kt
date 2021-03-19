package com.jacque.imagegallerytest.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class AlbumResponse(
    val data: ArrayList<AlbumDb> = ArrayList()
)

@Parcelize
data class AlbumDb(
    val id: String = "",
    val title: String = "",
    val images: ArrayList<ImageDb> = ArrayList()
) : Parcelable

@Parcelize
data class ImageDb(
    val link: String = "",
) : Parcelable
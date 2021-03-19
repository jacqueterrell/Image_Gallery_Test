package com.jacque.imagegallerytest.ui.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jacque.imagegallerytest.databinding.AlbumsListRowBinding
import com.jacque.imagegallerytest.model.AlbumDb

class AlbumsAdapter(
    private val albums: List<AlbumDb>,
    private val onRowClick: (album: AlbumDb) -> Unit
) :
    RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {

    inner class AlbumsViewHolder(private val binding: AlbumsListRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(album: AlbumDb) {

            binding.btnName.text = album.title

            binding.btnName.setOnClickListener {
                onRowClick(album)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AlbumsListRowBinding.inflate(inflater, parent, false)
        return AlbumsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.bind(albums[position])
    }

    override fun getItemCount() = albums.size
}
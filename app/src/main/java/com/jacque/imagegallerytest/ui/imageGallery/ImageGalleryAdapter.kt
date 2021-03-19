package com.jacque.imagegallerytest.ui.imageGallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.jacque.imagegallerytest.databinding.ImageListRowBinding
import com.jacque.imagegallerytest.model.ImageDb

class ImageGalleryAdapter(
    private val images: List<ImageDb>,
    private val onRowClick: (image: ImageDb) -> Unit
) : RecyclerView.Adapter<ImageGalleryAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(private val binding: ImageListRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: ImageDb) {

            Glide.with(binding.root.context)
                .load(image.link)
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(binding.ivImage)

            binding.layoutCard.setOnClickListener {
                onRowClick(image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ImageListRowBinding.inflate(inflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount() = images.size
}
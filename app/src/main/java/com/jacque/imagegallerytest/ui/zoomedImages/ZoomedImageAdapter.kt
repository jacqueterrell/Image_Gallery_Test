package com.jacque.imagegallerytest.ui.zoomedImages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.jacque.imagegallerytest.databinding.ZoomedImageListRowBinding
import com.jacque.imagegallerytest.model.ImageDb

/**
 * Allows us to pan and zoom our image. If the image is zoomed out, you can navigate to the next image from her without
 * having to go back a screen. Of course this is only if you have more than one image.
 */
class ZoomedImageAdapter(private val images: List<ImageDb>): RecyclerView.Adapter<ZoomedImageAdapter.ZoomedImageViewHolder>() {

    inner class ZoomedImageViewHolder(private val binding: ZoomedImageListRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(image: ImageDb) {
            Glide.with(binding.root.context)
                .load(image.link)
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(binding.ivZoomedImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZoomedImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ZoomedImageListRowBinding.inflate(inflater, parent, false)
        return ZoomedImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ZoomedImageViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount() = images.size
}
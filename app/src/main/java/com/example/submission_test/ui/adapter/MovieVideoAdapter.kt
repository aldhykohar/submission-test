package com.example.submission_test.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.submission_test.data.model.api.videos.VideosItem
import com.example.submission_test.databinding.ItemListVideosBinding
import com.example.submission_test.utils.UtilConstants.LAST_SEGMENT_VIDEO_THUMBNAIL
import com.example.submission_test.utils.UtilConstants.PATH_VIDEO_THUMBNAIL
import com.example.submission_test.utils.UtilExtensions.bindImage

/**
 * Created by aldhykohar on 2/18/2022.
 */

class MovieVideoAdapter(var onClick: (VideosItem) -> Unit) :
    ListAdapter<VideosItem, MovieVideoAdapter.ViewHolder>(DIFF_CALLBACK) {


    inner class ViewHolder(private val binding: ItemListVideosBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: VideosItem) {
            with(binding) {

                with(binding) {
                    root.context.bindImage(
                        videoIV,
                        PATH_VIDEO_THUMBNAIL + item.key + LAST_SEGMENT_VIDEO_THUMBNAIL
                    )
                }

                root.setOnClickListener { onClick(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemListVideosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<VideosItem>() {
            override fun areItemsTheSame(oldItem: VideosItem, newItem: VideosItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: VideosItem, newItem: VideosItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
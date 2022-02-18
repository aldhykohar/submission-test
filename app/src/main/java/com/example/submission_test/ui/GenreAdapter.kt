package com.example.submission_test.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.submission_test.data.model.api.genre.GenresItem
import com.example.submission_test.databinding.ItemListGenreBinding

/**
 * Created by rivaldy on 09/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

class GenreAdapter(var onClick: (GenresItem) -> Unit) :
    ListAdapter<GenresItem, GenreAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: ItemListGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: GenresItem) {
            with(binding) {
                genreTV.text = item.name

                root.setOnClickListener { onClick(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemListGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GenresItem>() {
            override fun areItemsTheSame(oldItem: GenresItem, newItem: GenresItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GenresItem, newItem: GenresItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
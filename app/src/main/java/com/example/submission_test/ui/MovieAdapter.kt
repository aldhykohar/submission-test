package com.example.submission_test.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.submission_test.data.model.api.movie.MovieResult
import com.example.submission_test.databinding.ItemGridMoviesBinding
import com.example.submission_test.utils.UtilConstants.PATH_IMAGE
import com.example.submission_test.utils.UtilExtensions.bindImage

/**
 * Created by aldhykohar on 2/18/2022.
 */

class MovieAdapter(var onClick: (MovieResult) -> Unit) :
    ListAdapter<MovieResult, MovieAdapter.ViewHolder>(DIFF_CALLBACK) {


    inner class ViewHolder(private val binding: ItemGridMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: MovieResult) {
            with(binding) {

                with(binding) {
                    root.context.bindImage(movieIV, PATH_IMAGE + item.backdropPath)
                    titleTV.text = item.title
                    descTV.text = item.overview
                }

                root.setOnClickListener { onClick(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemGridMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieResult>() {
            override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
                return oldItem == newItem
            }
        }
    }
}
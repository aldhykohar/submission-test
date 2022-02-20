package com.example.submission_test.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.submission_test.R
import com.example.submission_test.data.model.api.review.ReviewsItem
import com.example.submission_test.databinding.ItemGridMoviesBinding
import com.example.submission_test.databinding.ItemListReviewBinding
import com.example.submission_test.utils.UtilConstants.PATH_IMAGE
import com.example.submission_test.utils.UtilExtensions.bindImage
import com.example.submission_test.utils.UtilExtensions.formatDate

/**
 * Created by aldhykohar on 2/18/2022.
 */

class MovieReviewAdapter(var onClick: (ReviewsItem) -> Unit) :
    ListAdapter<ReviewsItem, MovieReviewAdapter.ViewHolder>(DIFF_CALLBACK) {


    inner class ViewHolder(private val binding: ItemListReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: ReviewsItem) {
            with(binding) {

                with(binding) {
                    val img = item.authorDetails?.avatarPath?.replaceFirst("/", "")
                    root.context.bindImage(reviewedIV, img)
                    reviewerNameTV.text =
                        root.context.getString(R.string.reviewed_by, item.authorDetails?.username)
                    reviewedDateTV.text =
                        root.context.getString(R.string.reviewed_on, item.createdAt.formatDate())
                    reviewTV.text = item.content
                }

                root.setOnClickListener { onClick(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemListReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ReviewsItem>() {
            override fun areItemsTheSame(oldItem: ReviewsItem, newItem: ReviewsItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ReviewsItem, newItem: ReviewsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
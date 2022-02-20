package com.example.submission_test.ui

import android.os.Bundle
import android.widget.AbsListView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.submission_test.base.BaseActivity
import com.example.submission_test.data.model.api.review.MovieReviewResponse
import com.example.submission_test.data.model.api.review.ReviewsItem
import com.example.submission_test.data.network.DataResource
import com.example.submission_test.databinding.ActivityReviewBinding
import com.example.submission_test.ui.adapter.MovieReviewAdapter
import com.example.submission_test.utils.UtilConstants
import com.example.submission_test.utils.UtilConstants.EXTRA_REVIEW
import com.example.submission_test.utils.UtilConstants.ID_MOVIE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewActivity : BaseActivity<ActivityReviewBinding>() {

    private val viewModel by viewModels<MovieViewModel>()

    private var reviewList: ArrayList<ReviewsItem> = arrayListOf()
    private var currentPage = 1
    private var currentId = 1

    var isLastPage = false
    var isScrolling = false

    private val adapterReview: MovieReviewAdapter by lazy {
        MovieReviewAdapter {
            val dialogDetail = DialogDetailReviewFragment()
            val bundle = Bundle()
            bundle.putParcelable(EXTRA_REVIEW, it)
            dialogDetail.arguments = bundle
            dialogDetail.show(supportFragmentManager, dialogDetail.tag)
        }
    }

    override fun getViewBinding(): ActivityReviewBinding =
        ActivityReviewBinding.inflate(layoutInflater)

    override fun initView() {
        initRecyclerview()
        initDataIntent()
    }

    private fun initDataIntent() {
        currentId = intent.getIntExtra(ID_MOVIE, 0)
        viewModel.getMovieReview(currentId, currentPage)
    }

    private fun initRecyclerview() {
        with(binding) {
            reviewRV.apply {
                setHasFixedSize(true)
                adapter = adapterReview
                addOnScrollListener(this@ReviewActivity.scrollListener)
            }
        }
    }

    override fun initObservers() {
        viewModel.movieReview.observe(this, {
            when (it) {
                is DataResource.Loading -> {
                }
                is DataResource.Success -> updateUIReview(it.value)
                is DataResource.Failure -> showFailure(it)
            }
        })
    }

    private fun updateUIReview(value: MovieReviewResponse) {
        if (value.results?.size != 0) currentPage++
        value.results?.let { reviewList.addAll(it) }
        adapterReview.submitList(reviewList)
    }

    override fun showFailure(failure: DataResource.Failure) {

    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLastPage = !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= UtilConstants.QUERY_PAGE_SIZE
            val shouldPaginate =
                isNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling
            if (shouldPaginate) {
                viewModel.getMovieReview(currentId, currentPage)
                isScrolling = false
            }
        }
    }

}
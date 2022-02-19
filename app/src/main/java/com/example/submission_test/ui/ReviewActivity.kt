package com.example.submission_test.ui

import androidx.activity.viewModels
import com.example.submission_test.base.BaseActivity
import com.example.submission_test.data.model.api.review.MovieReviewResponse
import com.example.submission_test.data.network.DataResource
import com.example.submission_test.databinding.ActivityReviewBinding
import com.example.submission_test.ui.adapter.MovieReviewAdapter
import com.example.submission_test.utils.UtilConstants.ID_MOVIE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewActivity : BaseActivity<ActivityReviewBinding>() {

    private val viewModel by viewModels<MovieViewModel>()

    private val adapterReview: MovieReviewAdapter by lazy {
        MovieReviewAdapter {

        }
    }

    override fun getViewBinding(): ActivityReviewBinding =
        ActivityReviewBinding.inflate(layoutInflater)

    override fun initView() {
        initDataIntent()
    }

    private fun initDataIntent() {
        val id = intent.getIntExtra(ID_MOVIE, 0)
        viewModel.getMovieReview(id)
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
        adapterReview.submitList(value.results)
        binding.reviewRV.adapter = adapterReview
    }

    override fun showFailure(failure: DataResource.Failure) {

    }

}
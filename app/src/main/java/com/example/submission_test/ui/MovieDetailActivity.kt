package com.example.submission_test.ui

import androidx.activity.viewModels
import com.example.submission_test.base.BaseActivity
import com.example.submission_test.data.model.api.movie.MovieResult
import com.example.submission_test.data.network.DataResource
import com.example.submission_test.databinding.ActivityMovieDetailBinding
import com.example.submission_test.utils.UtilConstants.EXTRA_MOVIE
import com.example.submission_test.utils.UtilConstants.PATH_IMAGE
import com.example.submission_test.utils.UtilExtensions.bindImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : BaseActivity<ActivityMovieDetailBinding>() {

    private val viewModel by viewModels<MovieViewModel>()

    override fun getViewBinding(): ActivityMovieDetailBinding =
        ActivityMovieDetailBinding.inflate(layoutInflater)

    override fun initView() {
        initDataIntent()
    }

    private fun initDataIntent() {
        val movieResult = intent.getParcelableExtra<MovieResult?>(EXTRA_MOVIE)
        movieResult?.let {
            viewModel.getMovieVideos(it.id ?: 0)
            with(binding) {
                bindImage(movieIV, PATH_IMAGE + it.posterPath)
                titleTV.text = it.title
                rateTV.text = it.voteAverage.toString()
                descTV.text = it.overview
            }
        }
    }

    override fun initObservers() {

    }

    override fun showFailure(failure: DataResource.Failure) {

    }
}
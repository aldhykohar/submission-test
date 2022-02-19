package com.example.submission_test.ui

import androidx.activity.viewModels
import com.example.submission_test.base.BaseActivity
import com.example.submission_test.data.model.api.movie.MovieResult
import com.example.submission_test.data.model.api.videos.MovieVideosResponse
import com.example.submission_test.data.network.DataResource
import com.example.submission_test.databinding.ActivityMovieDetailBinding
import com.example.submission_test.ui.adapter.MovieVideoAdapter
import com.example.submission_test.utils.UtilConstants.EXTRA_MOVIE
import com.example.submission_test.utils.UtilConstants.EXTRA_MOVIE_VIDEO
import com.example.submission_test.utils.UtilConstants.ID_MOVIE
import com.example.submission_test.utils.UtilConstants.PATH_IMAGE
import com.example.submission_test.utils.UtilExtensions.bindImage
import com.example.submission_test.utils.UtilExtensions.openActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : BaseActivity<ActivityMovieDetailBinding>() {

    private val viewModel by viewModels<MovieViewModel>()
    private var idMovie: Int? = null

    private val videoAdapter: MovieVideoAdapter by lazy {
        MovieVideoAdapter {
            openActivity(YoutubePlayerActivity::class.java) {
                putParcelable(EXTRA_MOVIE_VIDEO, it)
            }
        }
    }

    override fun getViewBinding(): ActivityMovieDetailBinding =
        ActivityMovieDetailBinding.inflate(layoutInflater)

    override fun initView() {
        initDataIntent()
        initClick()
    }

    private fun initDataIntent() {
        val movieResult = intent.getParcelableExtra<MovieResult?>(EXTRA_MOVIE)
        movieResult?.let {
            idMovie = it.id
            viewModel.getMovieVideos(it.id ?: 0)
            with(binding) {
                bindImage(movieIV, PATH_IMAGE + it.posterPath)
                titleTV.text = it.title
                rateTV.text = it.voteAverage.toString()
                descTV.text = it.overview
            }
        }
    }

    private fun initClick() {
        binding.reviewMB.setOnClickListener {
            openActivity(ReviewActivity::class.java) {
                idMovie?.let { id -> putInt(ID_MOVIE, id) }
            }
        }
    }

    override fun initObservers() {
        viewModel.movieVideos.observe(this, {
            when (it) {
                is DataResource.Loading -> {
                }
                is DataResource.Success -> updateUIVideo(it.value)
                is DataResource.Failure -> showFailure(it)
            }
        })
    }

    private fun updateUIVideo(value: MovieVideosResponse) {
        videoAdapter.submitList(value.results)
        binding.videoRV.adapter = videoAdapter
    }

    override fun showFailure(failure: DataResource.Failure) {

    }
}
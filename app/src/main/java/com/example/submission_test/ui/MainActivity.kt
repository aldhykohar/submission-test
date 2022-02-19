package com.example.submission_test.ui

import androidx.activity.viewModels
import com.example.submission_test.base.BaseActivity
import com.example.submission_test.data.model.api.genre.GenreResponse
import com.example.submission_test.data.model.api.genre.GenresItem
import com.example.submission_test.data.model.api.movie.MovieResponse
import com.example.submission_test.data.network.DataResource
import com.example.submission_test.databinding.ActivityMainBinding
import com.example.submission_test.ui.adapter.GenreAdapter
import com.example.submission_test.ui.adapter.MovieAdapter
import com.example.submission_test.utils.UtilConstants
import com.example.submission_test.utils.UtilExceptions.handleApiError
import com.example.submission_test.utils.UtilExtensions.openActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel by viewModels<MovieViewModel>()

    private val adapterGenre: GenreAdapter by lazy {
        GenreAdapter { genre: GenresItem, i: Int ->
            adapterGenre.setSelectedPos(i)
            viewModel.getMovieByGenre(null, genre.id ?: 0)
        }
    }

    private val adapterMovie: MovieAdapter by lazy {
        MovieAdapter {
            openActivity(MovieDetailActivity::class.java) {
                putParcelable(UtilConstants.EXTRA_MOVIE, it)
            }
        }
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initView() {
        viewModel.getGenre()
    }

    override fun initObservers() {
        viewModel.genre.observe(this, {
            when (it) {
                is DataResource.Loading -> {
                }
                is DataResource.Success -> updateUIGenre(it.value)
                is DataResource.Failure -> showFailure(it)
            }
        })

        viewModel.movies.observe(this, {
            when (it) {
                is DataResource.Loading -> {
                }
                is DataResource.Success -> updateUIMovie(it.value)
                is DataResource.Failure -> showFailure(it)
            }
        })
    }

    private fun updateUIMovie(value: MovieResponse) {
        adapterMovie.submitList(value.movieResults)
        binding.moviesRV.adapter = adapterMovie
    }

    private fun updateUIGenre(value: GenreResponse) {
        adapterGenre.submitList(value.genres)
        binding.genreRV.adapter = adapterGenre
        if (value.genres?.size!! > 1) {
            viewModel.getMovieByGenre(null, value.genres[0]?.id ?: 0)
        }
    }

    override fun showFailure(failure: DataResource.Failure) {
        handleApiError(failure)
    }
}
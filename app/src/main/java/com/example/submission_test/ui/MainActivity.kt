package com.example.submission_test.ui

import android.util.Log
import android.widget.AbsListView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.submission_test.base.BaseActivity
import com.example.submission_test.data.model.api.genre.GenreResponse
import com.example.submission_test.data.model.api.genre.GenresItem
import com.example.submission_test.data.model.api.movie.MovieResponse
import com.example.submission_test.data.model.api.movie.MovieResult
import com.example.submission_test.data.network.DataResource
import com.example.submission_test.databinding.ActivityMainBinding
import com.example.submission_test.ui.adapter.GenreAdapter
import com.example.submission_test.ui.adapter.MovieAdapter
import com.example.submission_test.utils.UtilConstants
import com.example.submission_test.utils.UtilConstants.QUERY_PAGE_SIZE
import com.example.submission_test.utils.UtilExceptions.handleApiError
import com.example.submission_test.utils.UtilExtensions.openActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel by viewModels<MovieViewModel>()
    private var currentPage = 1
    private var currentGenre: Int? = null

    var isLastPage = false
    var isScrolling = false

    private var movieList: ArrayList<MovieResult> = arrayListOf()

    private val adapterGenre: GenreAdapter by lazy {
        GenreAdapter { genre: GenresItem, i: Int ->
            movieList.clear()
            currentPage = 1
            currentGenre = genre.id
            adapterGenre.setSelectedPos(i)
            viewModel.getMovieByGenre(currentPage, currentGenre ?: 0)
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
        initRecyclerviewMovie()
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

    private fun initRecyclerviewMovie() {
        with(binding) {
            genreRV.setHasFixedSize(true)
            moviesRV.setHasFixedSize(true)
            binding.moviesRV.adapter = adapterMovie
            moviesRV.addOnScrollListener(this@MainActivity.scrollListener)
        }
    }

    private fun updateUIMovie(value: MovieResponse) {
        if (value.movieResults?.size != 0) currentPage++
        value.movieResults?.let { movieList.addAll(it) }
        adapterMovie.submitList(movieList.toList())
        isLastPage = currentPage == value.totalPages
    }

    private fun updateUIGenre(value: GenreResponse) {
        adapterGenre.submitList(value.genres)
        binding.genreRV.adapter = adapterGenre
        if (value.genres?.size!! > 1) {
            currentGenre = value.genres[0]?.id
            viewModel.getMovieByGenre(null, currentGenre ?: 0)
        }
    }

    override fun showFailure(failure: DataResource.Failure) {
        handleApiError(failure)
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
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate =
                isNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling
            if (shouldPaginate) {
                viewModel.getMovieByGenre(currentPage, currentGenre ?: 0)
                isScrolling = false
            }
        }
    }
}
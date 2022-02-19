package com.example.submission_test.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submission_test.data.DataRepository
import com.example.submission_test.data.model.api.genre.GenreResponse
import com.example.submission_test.data.model.api.movie.MovieResponse
import com.example.submission_test.data.model.api.review.MovieReviewResponse
import com.example.submission_test.data.model.api.videos.MovieVideosResponse
import com.example.submission_test.data.network.DataResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by rivaldy on 05/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    private val _genre: MutableLiveData<DataResource<GenreResponse>> = MutableLiveData()
    val genre: LiveData<DataResource<GenreResponse>> = _genre

    private val _movies: MutableLiveData<DataResource<MovieResponse>> = MutableLiveData()
    val movies: LiveData<DataResource<MovieResponse>> = _movies

    private val _movieVideos: MutableLiveData<DataResource<MovieVideosResponse>> = MutableLiveData()
    val movieVideos: LiveData<DataResource<MovieVideosResponse>> = _movieVideos

    private val _movieReview: MutableLiveData<DataResource<MovieReviewResponse>> = MutableLiveData()
    val movieReview: LiveData<DataResource<MovieReviewResponse>> = _movieReview


    fun getGenre() = viewModelScope.launch {
        _genre.value = DataResource.Loading
        _genre.value = dataRepository.getGenre()
    }

    fun getMovieByGenre(page: Int?, genre: Int) = viewModelScope.launch {
        _movies.value = DataResource.Loading
        _movies.value = dataRepository.getMoviesByGenre(page, genre)
    }

    fun getMovieVideos(id: Int) = viewModelScope.launch {
        _movieVideos.value = DataResource.Loading
        _movieVideos.value = dataRepository.getMovieVideos(id)
    }

    fun getMovieReview(id: Int, page: Int) = viewModelScope.launch {
        _movieReview.value = DataResource.Loading
        _movieReview.value = dataRepository.getMovieReview(id, page)
    }
}
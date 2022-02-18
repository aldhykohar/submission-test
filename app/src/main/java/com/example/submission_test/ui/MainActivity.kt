package com.example.submission_test.ui

import androidx.activity.viewModels
import com.example.submission_test.base.BaseActivity
import com.example.submission_test.data.model.api.genre.GenreResponse
import com.example.submission_test.data.network.DataResource
import com.example.submission_test.databinding.ActivityMainBinding
import com.example.submission_test.utils.UtilExceptions.handleApiError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel by viewModels<MovieViewModel>()

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initView() {
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
    }

    private fun updateUIGenre(value: GenreResponse) {

    }

    override fun showFailure(failure: DataResource.Failure) {
        handleApiError(failure)
    }
}
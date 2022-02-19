package com.example.submission_test.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.submission_test.base.BaseActivity
import com.example.submission_test.data.network.DataResource
import com.example.submission_test.databinding.ActivityReviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewActivity : BaseActivity<ActivityReviewBinding>() {
    override fun getViewBinding(): ActivityReviewBinding =
        ActivityReviewBinding.inflate(layoutInflater)

    override fun initView() {

    }

    override fun initObservers() {

    }

    override fun showFailure(failure: DataResource.Failure) {

    }

}
package com.example.submission_test.ui

import com.example.submission_test.base.BaseActivity
import com.example.submission_test.data.network.DataResource
import com.example.submission_test.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initView() {
    }

    override fun initObservers() {
    }

    override fun showFailure(failure: DataResource.Failure) {
    }
}
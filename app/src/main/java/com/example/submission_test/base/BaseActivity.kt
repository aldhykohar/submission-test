package com.example.submission_test.base

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.submission_test.data.network.DataResource
import com.example.submission_test.utils.UtilDialog


/**
 * Created by aldhykohar on 2/18/2022.
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    lateinit var binding: VB
    private val progressDialog: Dialog by lazy { UtilDialog.setProgressDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        initView()
        initObservers()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    abstract fun getViewBinding(): VB

    abstract fun initView()

    abstract fun initObservers()

    abstract fun showFailure(failure: DataResource.Failure)

    protected fun showLoading(isShown: Boolean) {
        if (isShown) showProgressDialog()
        else hideProgressDialog()
    }

    private fun showProgressDialog() {
        hideProgressDialog()
        progressDialog.show()
    }

    private fun hideProgressDialog() {
        if (progressDialog.isShowing) progressDialog.cancel()
    }
}
package com.example.submission_test.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.submission_test.R
import com.example.submission_test.base.BaseDialogFragment
import com.example.submission_test.data.model.api.review.ReviewsItem
import com.example.submission_test.data.network.DataResource
import com.example.submission_test.databinding.DialogDetailReviewFragmentBinding
import com.example.submission_test.utils.UtilConstants.EXTRA_REVIEW
import com.example.submission_test.utils.UtilExtensions.bindImage
import com.example.submission_test.utils.UtilExtensions.formatDate


/**
 * Created by aldhykohar on 2/20/2022.
 */
class DialogDetailReviewFragment : BaseDialogFragment<DialogDetailReviewFragmentBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> DialogDetailReviewFragmentBinding
        get() = DialogDetailReviewFragmentBinding::inflate

    override fun initView() {
        initData()
    }

    private fun initData() {
        val dataReview: ReviewsItem? = arguments?.getParcelable(EXTRA_REVIEW)
        dataReview?.let {
            with(binding) {
                val img = it.authorDetails?.avatarPath?.replaceFirst("/", "")
                root.context.bindImage(reviewedIV, img)
                reviewerNameTV.text =
                    root.context.getString(R.string.reviewed_by, it.authorDetails?.username)
                reviewedDateTV.text =
                    root.context.getString(R.string.reviewed_on, it.createdAt.formatDate())
                reviewTV.text = it.content
            }
        }
    }

    override fun initObserver() {

    }

    override fun showFailure(failure: DataResource.Failure) {

    }
}
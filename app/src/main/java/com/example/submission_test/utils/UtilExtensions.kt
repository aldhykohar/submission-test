package com.example.submission_test.utils

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.submission_test.R
import com.google.android.material.snackbar.Snackbar


/**
 * Created by aldhykohar on 2/18/2022.
 */
object UtilExtensions {
    fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
        val intent = Intent(this, it)
        intent.putExtras(Bundle().apply(extras))
        startActivity(intent)
    }

    fun View.isVisible(isVisible: Boolean) {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    fun Context.myToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun Context.myError(message: String) {
        Toast.makeText(this, "ERROR : $message", Toast.LENGTH_LONG).show()
    }

    fun View.showSnackBar(message: String, action: (() -> Unit)? = null) {
        val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        action?.let {
            snackBar.setAction(context.getString(R.string.retry)) {
                it()
            }
        }
        snackBar.show()
    }

    fun TextView.setPaintFlag() {
        this.paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }

    fun Context.bindImage(iv: ImageView, path: String?) {

        path?.let {
            Glide.with(this)
                .load(path)
                .placeholder(R.drawable.loading)
                .into(iv)
        } ?: let {
            Glide.with(this)
                .load(R.drawable.no_image)
                .placeholder(R.drawable.loading)
                .into(iv)
        }
    }
}
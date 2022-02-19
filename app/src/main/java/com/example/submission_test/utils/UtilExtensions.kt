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
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by aldhykohar on 2/18/2022.
 */
object UtilExtensions {
    private val localeID = Locale("in", "ID")

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

    fun String?.formatDate(): String? {
        val date1: Date?
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
        val pattern = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
        return try {
            date1 = dateFormat.parse(this ?: "")
            if (date1 != null) {
                pattern.format(date1)
            } else {
                ""
            }
        } catch (e: ParseException) {
            e.printStackTrace()
            ""
        }
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
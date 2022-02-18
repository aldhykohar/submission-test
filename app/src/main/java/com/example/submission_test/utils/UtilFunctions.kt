package com.example.submission_test.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import com.example.submission_test.utils.UtilConstants.LOG_MESSAGE
import java.io.ByteArrayOutputStream
import java.text.DateFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by aldhykohar on 2/18/2022.
 */
object UtilFunctions {
    private val localeID = Locale("in", "ID")

    fun getTimestamp(): Long {
        return Calendar.getInstance().time.time
    }

    fun loge(message: String) {
        Log.e(LOG_MESSAGE, message)
    }

    fun formatRupiahFloat(rupiah: Float): String? {
        val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(localeID)
        return formatRupiah.format(rupiah)
    }

    fun formatRupiah(original: String?): String {
        val strValue: String
        if (original == "") {
            return ""
        }
        val originalReplace = original?.replace(".00", "")
        val myBalance = originalReplace?.toLong() ?: 0F
        val total = "Rp "
        val valueRp = NumberFormat.getNumberInstance(localeID).format(myBalance).replace(",", ".")
        strValue = total + valueRp
        return strValue
    }

    fun formatRupiahNoRp(original: String?): String {
        val strValue: String
        if (original == "") {
            return ""
        }
        val originalReplace = original?.replace(".00", "")
        val myBalance = originalReplace?.toLong() ?: 0F
        strValue = NumberFormat.getNumberInstance(localeID).format(myBalance).replace(",", ".")
        return strValue
    }

    fun encodeImageBase64(bitmap: Bitmap): String {
        val byteArr = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArr)
        val byte = byteArr.toByteArray()
        return Base64.encodeToString(byte, Base64.DEFAULT)
    }

    fun decodeImageBase64(base64: String): Bitmap {
        val decodedString = Base64.decode(base64, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }

    fun dateFormatterNormal(calendar: Calendar): String {
        val sdf: DateFormat = SimpleDateFormat("EEEE, d MMMM yyy", localeID) // Sunday, 01 January 2021
        return sdf.format(calendar.time)
    }

    fun getCurrentTime(): String {
        val strFormat = "EEEE, d MMM yyyy"
        val dateFormat = SimpleDateFormat(strFormat, localeID)
        return dateFormat.format(Calendar.getInstance().time)
    }

    fun isStringNull(text: String?): String {
        return if (text != null) {
            if (text.isEmpty()) ""
            else text
        } else ""
    }

    fun isStringNullZero(text: String?): String {
        return if (text != null) {
            if (text.isEmpty()) "0"
            else text
        } else "0"
    }

    fun getDateTimeStamp(): Long {
        val strFormat = "ddMMyyyyhhmmSSS" // from this date -> 17/08/2020/02:15:474 to -> 170820200215474
        val dateFormat = SimpleDateFormat(strFormat, localeID)
        return dateFormat.format(Calendar.getInstance().time).toLong()
    }

}
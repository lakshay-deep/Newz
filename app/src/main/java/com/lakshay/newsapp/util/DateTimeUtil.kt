package com.lakshay.newsapp.util

import android.text.format.DateUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtil {

    private const val FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    fun getHoursAgoFromDate(date: String): String? {
        val sdf = SimpleDateFormat(FORMAT, Locale.ENGLISH)
        sdf.timeZone = TimeZone.getTimeZone("GMT")
        try {
            val time: Long = sdf.parse(date).time
            val now = System.currentTimeMillis()
            return DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)
                .toString()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return null
    }
}
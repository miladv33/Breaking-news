package com.pratama.baseandroid.utility

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun getNewsDate(date: String): String {
    val dateFormat = date.split("+").let {
        it[0].trim()
    }
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateTime: LocalDateTime = LocalDateTime.parse(dateFormat, formatter)
        val istoday = dateTime.toLocalDate().equals(LocalDate.now())
        if (istoday) {
            dateFormat.split(" ").last()
        } else {
            dateFormat.split(" ").let {
                it[0] + it[1]
            }
        }
    } else {
        dateFormat
    }
}
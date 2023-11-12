package com.bensek.topheadlines.utils

import android.util.Log
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

val availableDateFormats = listOf(
    "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'",
    "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'",
    "yyyy-MM-dd'T'HH:mm:ss'Z'"
)

fun convertToLocalDateTime(dateString: String): LocalDateTime? {
    for (format in availableDateFormats) {
        try {
            val formatter = DateTimeFormatter.ofPattern(format)
            return LocalDateTime.parse(dateString, formatter)
        } catch (e: DateTimeParseException) {
            Log.i(Constants.LOG_TAG, "Failed to parse date: ${e.message}")
        }
    }
    return null
}

fun formatDateTime(dateString: String): String? {
    val localDateTime = convertToLocalDateTime(dateString)
    val outputFormatter = DateTimeFormatter.ofPattern("EEE dd, MMM yyyy | HH:mm")
    return try {
        localDateTime?.format(outputFormatter)
    } catch (e: Exception) {
        Log.i(Constants.LOG_TAG, "Failed to format date: ${e.message}")
        null
    }
}
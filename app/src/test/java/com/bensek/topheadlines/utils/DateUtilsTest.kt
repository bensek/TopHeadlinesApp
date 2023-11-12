package com.bensek.topheadlines.utils

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.time.LocalDateTime

@RunWith(RobolectricTestRunner::class)
class DateUtilsTest {

    @Test
    fun testConvertToLocalDateTime() {
        // Test valid date string
        val validDateString = "2023-11-12T14:30:00Z"
        val expectedDateTime = LocalDateTime.of(2023, 11, 12, 14, 30)
        assertEquals(expectedDateTime, convertToLocalDateTime(validDateString))

        // Test invalid date string
        val invalidDateString = "invalid_date"
        assertNull(convertToLocalDateTime(invalidDateString))
    }

    @Test
    fun testFormatDateTime() {
        // Test valid date string
        val validDateString = "2023-11-12T14:30:00Z"
        val expectedFormattedDate = "Sun 12, Nov 2023 | 14:30"
        assertEquals(expectedFormattedDate, formatDateTime(validDateString))

        // Test invalid date string
        val invalidDateString = "invalid_date"
        assertNull(formatDateTime(invalidDateString))
    }
}
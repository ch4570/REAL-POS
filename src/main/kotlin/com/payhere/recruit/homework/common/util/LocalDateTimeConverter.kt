package com.payhere.recruit.homework.common.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Utility class for converting between LocalDateTime and String representations.
 */
abstract class LocalDateTimeConverter private constructor(){

    companion object {

        private val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

        /**
         * Converts a String representation of date and time to LocalDateTime.
         *
         * @param textDate The String representation of date and time.
         * @return The LocalDateTime object parsed from the input String.
         */
        fun convertToLocalDateTime(textDate: String): LocalDateTime =
            LocalDateTime.parse(textDate, dateFormat)
    }
}

package com.payletter.recruit.homework.common.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

abstract class LocalDateTimeConverter private constructor(){
    
    companion object {
        
        private val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        
        fun convertToLocalDateTime(textDate: String): LocalDateTime =
            LocalDateTime.parse(textDate, dateFormat)
    }
}
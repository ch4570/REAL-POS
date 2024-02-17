package com.payhere.recruit.homework.common.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
/**
 * LocalDateTime과 String 표현 간 변환을 위한 유틸리티 클래스입니다.
 */
abstract class LocalDateTimeConverter private constructor(){

    companion object {

        private val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

        /**
         * 날짜와 시간의 String 표현을 LocalDateTime으로 변환합니다.
         *
         * @param textDate 날짜와 시간의 String 표현입니다.
         * @return 입력된 String에서 파싱된 LocalDateTime 객체입니다.
         */
        fun convertToLocalDateTime(textDate: String): LocalDateTime =
            LocalDateTime.parse(textDate, dateFormat)
    }
}



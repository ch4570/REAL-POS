package com.payhere.recruit.homework

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class HomeworkApplication

fun main(args: Array<String>) {
	runApplication<HomeworkApplication>(*args)
}

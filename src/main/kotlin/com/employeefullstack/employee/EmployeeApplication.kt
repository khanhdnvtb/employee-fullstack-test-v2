package com.employeefullstack.employee

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class EmployeeApplication

fun main(args: Array<String>) {
    runApplication<EmployeeApplication>(*args)
}

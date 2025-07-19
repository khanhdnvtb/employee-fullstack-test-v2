package com.employeefullstack.employee

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@TestPropertySource(properties = [
    "import.initial-delay=3600000",
    "import.fixed-delay=3600000"
])
class EmployeeApplicationTests {

    @Test
    fun contextLoads() {
    }

}

package com.employeefullstack.employee.controller

import com.employeefullstack.employee.service.AIService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class AIController(private val aiService: AIService) {
    @PostMapping("/products/ask")
    @ResponseBody
    fun askAI(@RequestBody body: Map<String, String>): Map<String, String> {
        val question = body["question"] ?: ""
        val answer = aiService.askAIForProducts(question)
        return mapOf("answer" to answer)
    }
}
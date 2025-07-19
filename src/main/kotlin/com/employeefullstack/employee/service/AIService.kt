package com.employeefullstack.employee.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.core.env.Environment
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class AIService(private val env: Environment) {
    fun askAIForProducts(question: String): String {
        val geminiApiKey = env.getProperty("GEMINI_API_KEY") ?: ""
        val prompt =
            "You are a software development assistant. Please answer the following user request in detail and clearly:\n\n" + question
        val url = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-pro:generateContent?key=$geminiApiKey"
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val requestBody = mapOf(
            "contents" to listOf(
                mapOf("parts" to listOf(mapOf("text" to prompt)))
            )
        )
        val entity = HttpEntity(requestBody, headers)
        val restTemplate = RestTemplate()
        return try {
            val response = restTemplate.postForEntity(url, entity, String::class.java)
            val json = response.body ?: return "No response received from Gemini API."
            val mapper = ObjectMapper()
            val root = mapper.readTree(json)
            val text = root["candidates"]?.get(0)?.get("content")?.get("parts")?.get(0)?.get("text")?.asText()
            text ?: "No response from Gemini."
        } catch (e: Exception) {
            return e.message.toString();
        }
    }
}
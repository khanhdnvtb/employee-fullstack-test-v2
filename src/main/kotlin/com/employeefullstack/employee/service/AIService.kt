package com.employeefullstack.employee.service

import com.employeefullstack.employee.dto.GeminiResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class AIService(@Value("\${gemini.api-key}") private val geminiApiKey: String) {

    private val logger = LoggerFactory.getLogger(AIService::class.java)
    companion object {
        private const val BASE_URL = "https://generativelanguage.googleapis.com/v1beta"
        private const val MODEL_NAME = "gemini-2.0-flash"
        private const val PROMPT_FORM = "You are a software development assistant. Please answer the following user request in detail and clearly:\n\n"
    }
    fun askAIForProducts(question: String): String {

        val prompt = buildPrompt(question)
        val geminiUrl = "$BASE_URL/models/$MODEL_NAME:generateContent?key=$geminiApiKey"

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
            val response = restTemplate.postForEntity(geminiUrl, entity, String::class.java)
            val json = response.body ?: return "No response received from Gemini API."
            val objectMapper = ObjectMapper()
            val geminiResponse = objectMapper.readValue(json, GeminiResponse::class.java)
            val text = geminiResponse.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.text

            text ?: "No response from Gemini API."
        } catch (ex: Exception) {
            logger.error("Error calling Gemini API", ex)
            return "Error: ${ex.localizedMessage}"
        }
    }

    private fun buildPrompt(question: String): String =
        "$PROMPT_FORM$question"
}

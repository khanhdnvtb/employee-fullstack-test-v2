package com.employeefullstack.employee.dto
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
@JsonIgnoreProperties(ignoreUnknown = true)
data class GeminiResponse(
    @JsonProperty("candidates")
    val candidates: List<Candidate> = emptyList(),
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Candidate(
    @JsonProperty("content")
    val content: Content,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Content(
    @JsonProperty("parts")
    val parts: List<Part>,
)

data class Part(
    @JsonProperty("text")
    val text: String
)

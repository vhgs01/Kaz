package br.com.kaz.model.courses

data class Checklist(
    var completed: Boolean?,
    val description: String,
    val title: String
)
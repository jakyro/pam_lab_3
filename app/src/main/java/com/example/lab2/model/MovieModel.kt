package com.example.lab2.model

data class MovieModel(
    val id: String,
    val name: String,
    val category: String,
    val thumbnail: String,
    val year: String,
    val length: String,
    val rating: String?
)
package com.example.lab2.model

data class SubmitMovieModel(
    val name: String,
    val category: String,
    val thumbnail: String,
    val year: String,
    val length: String
) {
    fun valid(): Boolean {
        return name.isNotEmpty()
                && category.isNotEmpty()
                && thumbnail.isNotEmpty()
                && year.isNotEmpty()
                && length.isNotEmpty()
    }
}
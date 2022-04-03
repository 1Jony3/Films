package com.example.films.model.data

import java.io.Serializable

data class Film(
    val id: Int,
    val localized_name: String,
    val name: String,
    val year: Int,
    val rating: Float,
    val image_url: String? = null,
    val description: String,
    val genres: List<String>?): Serializable
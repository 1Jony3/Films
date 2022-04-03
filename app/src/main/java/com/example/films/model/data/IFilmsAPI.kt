package com.example.films.model.data

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface IFilmsAPI {
    @GET("/sequeniatesttask/films.json")
    fun fetchAllFilms(): Call<JsonObject>
}
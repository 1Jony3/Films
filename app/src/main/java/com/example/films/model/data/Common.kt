package com.example.films.model.data

object Common {
    private const val BASE_URL = "https://s3-eu-west-1.amazonaws.com"
    val retrofitService: IFilmsAPI
        get() = RetrofitClient.getClient(BASE_URL).create(IFilmsAPI::class.java)
}
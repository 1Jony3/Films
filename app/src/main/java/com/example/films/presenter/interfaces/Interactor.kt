package com.example.films.presenter.interfaces

import com.example.films.model.data.Film

interface Interactor {
    fun setData(film: List<Film>)
}
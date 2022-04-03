package com.example.films.presenter.clicker

import com.example.films.model.data.Film

interface OnFilmClickListener {
    fun onFilmClick(film: Film)
}


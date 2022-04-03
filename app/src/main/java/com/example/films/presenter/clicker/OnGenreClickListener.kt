package com.example.films.presenter.clicker

interface OnGenreClickListener {
    var selectedPosition: Int?
    fun onGenreClick(genre: String, position: Int)
}
package com.example.films.presenter.list

import android.view.View
import androidx.recyclerview.widget.ConcatAdapter
import com.example.films.view.MainActivity
import com.example.films.presenter.adapter.FilmsAdapter
import com.example.films.presenter.adapter.GenresAdapter
import com.example.films.presenter.adapter.TitleAdapter
import com.example.films.presenter.clicker.OnFilmClickListener
import com.example.films.presenter.clicker.OnGenreClickListener
import com.example.films.model.data.Film
import com.example.films.presenter.interfaces.Presenter

class ListPresenter(private var view: View, private var filmList: List<Film>): Presenter {

    private val filmClickListener = object : OnFilmClickListener {
        override fun onFilmClick(film: Film) {
            (view.context as MainActivity).showDetails(film)
        }
    }
    private val genreClickListener = object : OnGenreClickListener {
        override var selectedPosition: Int? = null

        override fun onGenreClick(genre: String, position: Int) {
            if (selectedPosition == position) {
                selectedPosition = null
                filmsAdapter.filterFilms(null)
            } else {
                selectedPosition = position
                filmsAdapter.filterFilms(sortFilmsByGenres(genre))
            }
        }
    }
    private var filmsAdapter = FilmsAdapter(filmClickListener, filmList)
    private var genresAdapter = GenresAdapter(genreClickListener, creatingListOfGenres())

    private fun creatingListOfGenres(): List<String> {
        val genres: MutableList<String> = mutableListOf()
        for (film in filmList) {
            film.genres?.let { genres.addAll(it) }
        }

        return genres.distinct()
    }

    private fun sortFilmsByGenres(genre: String): MutableList<Film> {
        val filmsByGenres: MutableList<Film> = mutableListOf()
        for (film in filmList) {
            film.genres?.let { if (it.indexOf(genre) != -1) filmsByGenres.addAll(listOf(film)) }
        }
        return filmsByGenres
    }

    override fun get(): ConcatAdapter {
        return ConcatAdapter(
            TitleAdapter("Жанры"),
            genresAdapter,
            TitleAdapter("Фильмы"),
            filmsAdapter
        )
    }
}
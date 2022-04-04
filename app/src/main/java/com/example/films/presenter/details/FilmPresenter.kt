package com.example.films.presenter.details

import com.bumptech.glide.Glide
import com.example.films.R
import com.example.films.model.data.Film
import com.example.films.presenter.interfaces.Presenter
import com.example.films.view.DetailsFragment


class FilmPresenter(private var details: DetailsFragment): Presenter {

    private lateinit var filmsList: Film

    override fun get(): Film {
        return filmsList
    }

    fun outPutData(){
        val glideManager = Glide.with(details)
        details.nameText.text = get().name
        details.lNameText.text = get().localized_name
        details.yearText.text = get().year.toString()
        details.ratingText.text = get().rating.toString()
        details.description.text = get().description

        get().image_url.let{
            glideManager
            .load(it)
            .error(R.drawable.not_load)
            .placeholder(R.drawable.not_load)
            .into(details.image)
        }
    }

    fun setData(film: List<Film>) {
        this.filmsList = film[0]
    }
}
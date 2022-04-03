package com.example.films.view

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.films.R
import com.example.films.model.data.Film
import com.example.films.presenter.interfaces.IView
import com.example.films.presenter.details.FilmPresenter

class DetailsFragment : Fragment(), IView {

    private lateinit var filmPresenter : FilmPresenter
    lateinit var nameText: TextView
    lateinit var lNameText: TextView
    lateinit var yearText: TextView
    lateinit var ratingText: TextView
    lateinit var description: TextView
    lateinit var image: ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        d("lol", "onCreate details")

        filmPresenter = FilmPresenter(this)
        filmPresenter.setData(listOf(arguments!!.getSerializable("keyFilm") as Film))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        d("lol", "onCreateView details")
        if(savedInstanceState == null) loadData(view)
        return view
    }

    override fun loadData(view: View) {
        nameText = view.findViewById(R.id.NameText)
        lNameText = view.findViewById(R.id.LNameText)
        yearText = view.findViewById(R.id.YearText)
        ratingText = view.findViewById(R.id.RatingText)
        description = view.findViewById(R.id.DescriptionText)
        image = view.findViewById(R.id.Image)

        filmPresenter.outPutData()
    }

}
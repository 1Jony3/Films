package com.example.films.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.films.R
import com.example.films.model.data.Film
import com.example.films.presenter.clicker.OnFilmClickListener


class FilmsAdapter(private val onClickListener: OnFilmClickListener, private val filmsList: List<Film>): RecyclerView.Adapter<FilmsAdapter.FilmHolder>() {

    private var films: List<Film> = filmsList

    fun filterFilms(filmsByGenre: MutableList<Film>?){
        films = filmsByGenre ?: filmsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {
        return FilmHolder(LayoutInflater.from(parent.context).inflate(R.layout.films_item, parent, false))
    }

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        val film = films[position]
        holder.itemView.setOnClickListener { onClickListener.onFilmClick(film) }
        val layoutParams: StaggeredGridLayoutManager.LayoutParams = holder.itemView.getLayoutParams() as StaggeredGridLayoutManager.LayoutParams
        layoutParams.isFullSpan = false
        holder.bind(film)
    }

    override fun getItemCount() = films.size

    class FilmHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val nameText: TextView = itemView.findViewById(R.id.nameText)
        private var image: ImageView = itemView.findViewById(R.id.image)
        private var glideManager: RequestManager = Glide.with(itemView.context)

        fun bind(film: Film) {

            nameText.text = film.localized_name
            film.image_url.let { glideManager.load(it).into(image)}
        }
    }

}
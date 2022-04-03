package com.example.films.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.films.presenter.clicker.OnGenreClickListener
import com.example.films.R

class GenresAdapter(private val onClickListener: OnGenreClickListener, private val genres: List<String>): RecyclerView.Adapter<GenresAdapter.GenresHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresHolder {
        return GenresHolder(LayoutInflater.from(parent.context).inflate(R.layout.genres_item, parent, false))
    }

    override fun onBindViewHolder(holder: GenresHolder, position: Int) {
        val genre = genres[position]
        val layoutParams: StaggeredGridLayoutManager.LayoutParams = holder.itemView.getLayoutParams() as StaggeredGridLayoutManager.LayoutParams
        layoutParams.isFullSpan = true
        holder.bind(genre, onClickListener, position)
    }

    override fun getItemCount() = genres.size


    class GenresHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val genre: Button = itemView.findViewById(R.id.genres_button)

        private fun changeColor(position: Int, selectedPosition: Int?)
        {
            if (selectedPosition == position)
                genre.setBackgroundColor(ContextCompat.getColor(itemView.context,(R.color.minifon)))
            else genre.setBackgroundColor(ContextCompat.getColor(itemView.context,(R.color.temnee)))
        }

        fun bind(string: String, onClickListener: OnGenreClickListener, position: Int) {
            changeColor(position, onClickListener.selectedPosition)
            genre.text = string
            genre.setOnClickListener { onClickListener.onGenreClick(string, position) }
        }
    }
}
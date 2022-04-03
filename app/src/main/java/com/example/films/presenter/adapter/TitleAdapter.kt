package com.example.films.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.films.R

class TitleAdapter(private val title: String): RecyclerView.Adapter<TitleAdapter.TitleHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleHolder {
        return TitleHolder(LayoutInflater.from(parent.context).inflate(R.layout.title_item, parent, false))
    }

    override fun onBindViewHolder(holder: TitleHolder, position: Int) {
        val layoutParams: StaggeredGridLayoutManager.LayoutParams = holder.itemView.getLayoutParams() as StaggeredGridLayoutManager.LayoutParams
        layoutParams.isFullSpan = true
        holder.bind(title)
    }

    override fun getItemCount() = 1

    class TitleHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.title)

        fun bind(string: String) {
            title.text = string
        }
    }
}
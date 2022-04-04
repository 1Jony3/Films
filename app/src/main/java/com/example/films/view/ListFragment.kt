package com.example.films.view

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.films.R
import com.example.films.presenter.interfaces.IView
import com.example.films.presenter.list.FilmListInteractor

class ListFragment : Fragment(), IView {

    private lateinit var interactor: FilmListInteractor
    var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        d("lol", "list onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        (activity as AppCompatActivity).supportActionBar?.let { it.title = "Главная" }

        loadData(view)

        return view
    }

    override fun loadData(view: View) {
        recyclerView = view.findViewById(R.id.filmList)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView!!.layoutManager = layoutManager

        interactor = FilmListInteractor(view, this)
        interactor.getFilmsFromAPI()//выводит и сохраняет список
    }
}
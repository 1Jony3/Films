package com.example.films.presenter.list

import android.view.View
import com.example.films.model.data.Common
import com.example.films.model.data.Film
import com.example.films.presenter.interfaces.Interactor
import com.example.films.view.ListFragment
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FilmListInteractor(private var view: View, private var list: ListFragment) : Interactor {

    lateinit var presenter: ListPresenter

    fun getFilmsFromAPI() {
        val thread = Thread {
            try {
                Common.retrofitService.fetchAllFilms().enqueue(object : Callback<JsonObject> {
                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {
                        val list = convertFilms(response.body()?.get("films") as JsonArray)
                        setData(list.sortedBy { it.localized_name })
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        thread.start()
    }

    private fun convertFilms(films: JsonArray): List<Film> {
        return Gson().fromJson(films.toString(), object : TypeToken<List<Film?>?>() {}.type)
    }

    override fun setData(film: List<Film>) {
        presenter = ListPresenter(view, film)

        list.recyclerView!!.adapter = presenter.get()
    }
}
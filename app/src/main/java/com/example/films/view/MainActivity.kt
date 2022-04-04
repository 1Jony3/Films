package com.example.films.view

import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.films.R
import com.example.films.model.data.Film

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        d("lol", "MainActivity onCreate")

        supportFragmentManager.beginTransaction().replace(R.id.main, ListFragment()).commitAllowingStateLoss()

    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.main, fragment).addToBackStack("fDetails").commitAllowingStateLoss()
    }

    fun showDetails(film: Film) {
        val bundle = Bundle()
        bundle.putSerializable("keyFilm", film)

        val detailsFragment = DetailsFragment()
        detailsFragment.arguments = bundle

        supportActionBar!!.title = film.localized_name

        showFragment(detailsFragment)
    }
}


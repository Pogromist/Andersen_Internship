package com.example.andersen_internship

import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import androidx.room.Room
import com.example.andersen_internship.mvp.ProfilePresenter
import com.example.andersen_internship.mvp.ProfileView
import com.example.andersen_internship.roomdatabase.Collection
import com.example.andersen_internship.roomdatabase.MovieDao
import com.example.andersen_internship.roomdatabase.MovieDatabase
import kotlinx.android.synthetic.main.fragment_profile.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import java.lang.StringBuilder
import java.util.ArrayList

class ProfileFragment : MvpAppCompatFragment(), ProfileView {

    @InjectPresenter
    lateinit var profilePresenter: ProfilePresenter
    private var myNotification = MyNotification.instance
    lateinit var movieDatabase: MovieDatabase
    lateinit var movieDao: MovieDao

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        movieDatabase =
            Room.databaseBuilder(requireContext(), MovieDatabase::class.java, "movie_database")
                .allowMainThreadQueries()
                .build()
        movieDao = movieDatabase.movieDao()

        btnAdd.setOnClickListener {
            AsyncTaskInsertCollection().execute()
        }

        btnGet.setOnClickListener {
            showToast(movieDao.allCollections())
        }

        btnDelete.setOnClickListener {
            AsyncTaskDeleteCollection().execute()
        }

        btnShowNotification.setOnClickListener {
            profilePresenter.onNotificationClick(requireActivity())
        }

        btnRetrofitRequest.setOnClickListener {
            profilePresenter.loadingMovies(requireContext())
        }
    }

    private fun showToast(allCollections: List<Collection>) {
        var builder = StringBuilder()
        for (element in allCollections)
            builder.append(element.toString()).append("\n")

        Toast.makeText(requireContext(), builder.toString(), Toast.LENGTH_LONG).show()
    }

    private fun createCollection(): List<Collection> {
        var collections: ArrayList<Collection> = ArrayList()
        for (i in 0..15)
            collections.add(Collection(i, "collection_$i"))
        return collections
    }

    inner class AsyncTaskInsertCollection : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            movieDao.insertCollection(createCollection())
            return null
        }
    }

    inner class AsyncTaskDeleteCollection : AsyncTask<List<Collection>, Void, Void>() {
        override fun doInBackground(vararg params: List<Collection>?): Void? {
            movieDao.deleteCollection(Collection(1, "collection_1"))
            return null
        }

    }

    override fun showMovies(data: PopularMovies) {
        Toast.makeText(context, "Success request", Toast.LENGTH_SHORT).show()
    }

    override fun onFailure() {
        Toast.makeText(context, "Fail request", Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun showNotification(activity: FragmentActivity) {
        myNotification.notification(requireActivity())
    }
}






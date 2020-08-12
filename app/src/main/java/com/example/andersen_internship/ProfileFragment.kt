package com.example.andersen_internship

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andersen_internship.mvp.ProfilePresenter
import com.example.andersen_internship.mvp.ProfileView
import com.example.andersen_internship.roomdatabase.MainViewModel
import com.example.andersen_internship.roomdatabase.MovieData
import com.example.andersen_internship.roomdatabase.MovieDataAdapter
import com.example.andersen_internship.roomdatabase.MovieDatabase
import kotlinx.android.synthetic.main.fragment_profile.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class ProfileFragment : MvpAppCompatFragment(), ProfileView {

    @InjectPresenter
    lateinit var profilePresenter: ProfilePresenter
    private var myNotification = MyNotification.instance
    private var viewModel: MainViewModel? = null
    private var movieAdapter: MovieDataAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        var databaseInstance = MovieDatabase.getDatabaseInstance(requireContext())
        viewModel?.setInstanceOfDb(databaseInstance)
        initViews()
        observerViewModel()

        btnShowNotification.setOnClickListener {
            profilePresenter.onNotificationClick(requireActivity())
        }

        btnRetrofitRequest.setOnClickListener {
            profilePresenter.loadingMovies(requireContext())
        }

        btnSave.setOnClickListener {
            saveData()
        }

        btnGet.setOnClickListener {
            viewModel?.getMovieData()
        }
    }

    private fun saveData() {
        var title = edtTitle.text.trim().toString()
        var duration = edtDuration.text.trim().toString()
        edtTitle.setText("")
        edtDuration.setText("")
        if (title.isNullOrBlank() || duration.isNullOrBlank()) {
            Toast.makeText(requireContext(), "Please enter valid details", Toast.LENGTH_LONG).show()
        } else {

            var person = MovieData(title = title, duration = duration.toInt())
            viewModel?.saveDataIntoDb(person)

        }
    }

    private fun initViews() {
        rvSavedRecords.layoutManager = LinearLayoutManager(requireContext())
        movieAdapter = MovieDataAdapter() {
            it.let {
                viewModel?.deleteMovie(it)
            }
        }
        rvSavedRecords.adapter = movieAdapter
    }

    private fun observerViewModel() {
        viewModel?.moviesList?.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
                handleData(it)
            } else {
                handleZeroCase()
            }
        })
    }

    private fun handleData(data: List<MovieData>) {
        rvSavedRecords.visibility = View.VISIBLE
        movieAdapter?.setData(data)
    }

    private fun handleZeroCase() {
        rvSavedRecords.visibility = View.GONE
        Toast.makeText(requireContext(), "No Records Found", Toast.LENGTH_LONG).show()
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






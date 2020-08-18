package com.example.andersen_internship

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import com.example.andersen_internship.mvp.ProfilePresenter
import com.example.andersen_internship.mvp.ProfileView
import com.example.andersen_internship.roomdatabase.DatabaseRepository
import com.example.andersen_internship.roomdatabase.Movie
import kotlinx.android.synthetic.main.fragment_profile.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class ProfileFragment : MvpAppCompatFragment(), ProfileView {

    @InjectPresenter
    lateinit var profilePresenter: ProfilePresenter
    private var myNotification = MyNotification.instance

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        DatabaseRepository.buildDatabase(requireContext())

        btnAdd.setOnClickListener {
            profilePresenter.getMovieDataFromDatabase(requireContext())
        }

        btnShowNotification.setOnClickListener {
            profilePresenter.onNotificationClick(requireActivity())
        }

        btnRetrofitRequest.setOnClickListener {
            profilePresenter.loadingMovies(requireContext())
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

    override fun showDatabaseElementsToast(data: ArrayList<Movie>) {
        Toast.makeText(context, data.toString(), Toast.LENGTH_LONG).show()
    }

    override fun showSuccessMessage() {
        Toast.makeText(context, "Successfull adding data", Toast.LENGTH_SHORT).show()
    }
}

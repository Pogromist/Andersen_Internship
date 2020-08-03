package com.example.andersen_internship

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.andersen_internship.mvp.ProfilePresenter
import com.example.andersen_internship.mvp.ProfileView
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_profile.*
import moxy.MvpAppCompatFragment

class ProfileFragment : MvpAppCompatFragment(), ProfileView {

    private var profilePresenter = ProfilePresenter()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        btnShowNotification.setOnClickListener {
           profilePresenter.onNotificationClick(requireActivity())
        }

        btnRetrofitRequest.setOnClickListener {
            profilePresenter.onRetrofitRequestClick(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.dispose()
    }

    override fun retrofitRequest() {}

    override fun notification() {}
}






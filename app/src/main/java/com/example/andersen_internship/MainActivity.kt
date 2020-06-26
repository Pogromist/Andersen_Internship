package com.example.andersen_internship

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        bottom_navigation_menu.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment2 -> replaceFragment(HomeFragment())
                R.id.profileFragment -> replaceFragment(ProfileFragment())
                R.id.settingsFragment2 -> replaceFragment(SettingsFragment())
                R.id.progressBarFragment -> replaceFragment(ProgressBarFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .commit()
    }
}

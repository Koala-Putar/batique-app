package com.example.batiqueapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.batiqueapp.ui.camera.CameraActivity
import com.example.batiqueapp.ui.explore.ExploreFragment
import com.example.batiqueapp.ui.favorite.FavoriteFragment
import com.example.batiqueapp.ui.history.HistoryFragment
import com.example.batiqueapp.ui.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val exploreFragment = ExploreFragment()
    private val favoriteFragment = FavoriteFragment()
    private val historyFragment = HistoryFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(homeFragment)

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_nav)
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.miHome -> replaceFragment(homeFragment)
                R.id.miExplore -> replaceFragment(exploreFragment)
                R.id.miCamera -> {
                    val intent = Intent(this@MainActivity, CameraActivity::class.java)
                    startActivity(intent)
                }
                R.id.miFavorite -> replaceFragment(favoriteFragment)
                R.id.miHistory -> replaceFragment(historyFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        if ( fragment != null ){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}
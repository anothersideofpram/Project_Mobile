package com.example.project_pramana

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.project_pramana.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            binding.main.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            binding.bottomNavigation.setPadding(0, 0, 0, systemBars.bottom)

            insets
        }

        if (savedInstanceState == null) {
            openFragment(HomeFragment(), addToBackStack = false)
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    openFragment(HomeFragment(), addToBackStack = false)
                    true
                }
                R.id.nav_bantuan -> {
                    openFragment(GotongRoyongFragment(), addToBackStack = false)
                    true
                }
                R.id.nav_lapor -> {
                    openFragment(LaporFragment(), addToBackStack = false)
                    true
                }
                R.id.nav_forum -> {
                    openFragment(ForumFragment(), addToBackStack = false)
                    true
                }
                R.id.nav_profil -> {
                    openFragment(ProfilFragment(), addToBackStack = false)
                    true
                }
                else -> false
            }
        }
    }

    fun openFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
            .replace(R.id.fragment_container, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    fun selectBottomNav(itemId: Int) {
        binding.bottomNavigation.selectedItemId = itemId
    }
}
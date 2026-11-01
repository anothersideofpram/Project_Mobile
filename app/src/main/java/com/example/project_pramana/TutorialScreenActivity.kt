package com.example.project_pramana

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.project_pramana.Tutorial.Tutorial1Fragment
import com.example.project_pramana.Tutorial.Tutorial2Fragment
import com.example.project_pramana.Tutorial.Tutorial3Fragment
import com.example.project_pramana.Tutorial.TutorialFragmentAdapter
import com.example.project_pramana.databinding.ActivityTutorialScreenBinding

class TutorialScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTutorialScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fragments = listOf<Fragment>(
            Tutorial1Fragment(),
            Tutorial2Fragment(),
            Tutorial3Fragment()
        )

        val adapter = TutorialFragmentAdapter(this, fragments)
        binding.tutorialMessageViewPager.adapter = adapter
        binding.dotIndicator.setViewPager2(binding.tutorialMessageViewPager)
    }
}
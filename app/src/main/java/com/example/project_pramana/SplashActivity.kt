package com.example.project_pramana

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_pramana.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private var isNavigated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Handler(Looper.getMainLooper()).postDelayed({
            checkLoginStatus()
        }, 2500)
    }

    private fun checkLoginStatus() {
        if (isNavigated) return
        isNavigated = true

        val sharedPreferences = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isLogin = sharedPreferences.getBoolean("isLogin", false)

        if (isLogin) {
            val intent = Intent(this, MainActivity::class.java)
            val savedEmail = sharedPreferences.getString("email", "")
            val savedPassword = sharedPreferences.getString("password", "")
            intent.putExtra("email", savedEmail)
            intent.putExtra("password", savedPassword)
            startActivity(intent)
        } else {
            val intent = Intent(this, HalamanLoginActivity::class.java)
            startActivity(intent)
        }
        finish()
    }
}
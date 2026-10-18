package com.example.project_pramana

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_pramana.databinding.ActivityDetailBinding

class ActivityDetail : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set up Toolbar pada ActivityDetail
        setSupportActionBar(binding.toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbarDetail.setNavigationOnClickListener {
            finish()
        }

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        val nama = intent.getStringExtra("nama")
            ?: sharedPref.getString("nama", "")
            ?: sharedPref.getString("registered_nama", "Alex Chen")

        val email = intent.getStringExtra("email")
            ?: sharedPref.getString("email", "email@example.com")

        val password = intent.getStringExtra("password")
            ?: sharedPref.getString("password", "")

        val phone = intent.getStringExtra("noHp")
            ?: sharedPref.getString("noHp", "")
            ?: sharedPref.getString("registered_phone", "08123456789")

        binding.txtNamaDetail.text = if (!nama.isNullOrEmpty()) nama else "Alex Chen"
        binding.txtEmailDetail.text = if (!email.isNullOrEmpty()) email else "Tidak ada data"
        binding.txtPhoneDetail.text = if (!phone.isNullOrEmpty()) phone else "08123456789"

        binding.txtPasswordDetail.text = if (!password.isNullOrEmpty()) {
            "*".repeat(password.length)
        } else {
            "Tidak ada data"
        }
    }
}
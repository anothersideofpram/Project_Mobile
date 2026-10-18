package com.example.project_pramana

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_pramana.databinding.ActivityHalamanLoginBinding

class HalamanLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHalamanLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHalamanLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.txtToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email dan Password harus diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Format email tidak valid!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.length < 6) {
                Toast.makeText(this, "Password minimal 6 karakter!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sharedPreferences = getSharedPreferences("user_pref", MODE_PRIVATE)
            val regEmail = sharedPreferences.getString("registered_email", "")
            val regPassword = sharedPreferences.getString("registered_password", "")
            val regNama = sharedPreferences.getString("registered_nama", "")
            val regPhone = sharedPreferences.getString("registered_phone", "")

            if (!regEmail.isNullOrEmpty() && email != regEmail) {
                Toast.makeText(this, "Email tidak terdaftar! Silakan registrasi terlebih dahulu.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!regPassword.isNullOrEmpty() && password != regPassword) {
                Toast.makeText(this, "Password salah!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nama = if (!regNama.isNullOrEmpty()) regNama else email.substringBefore("@")
            val phone = if (!regPhone.isNullOrEmpty()) regPhone else "08123456789"

            sharedPreferences.edit {
                putBoolean("isLogin", true)
                putString("nama", nama)
                putString("email", email)
                putString("password", password)
                putString("noHp", phone)
            }

            Log.d("LoginData", "Nama: $nama, Email: $email, Phone: $phone - Login Berhasil")
            Toast.makeText(this, "Login Berhasil! Selamat datang, $nama", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("nama", nama)
            intent.putExtra("email", email)
            intent.putExtra("password", password)
            intent.putExtra("noHp", phone)
            startActivity(intent)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        val sharedPreferences = getSharedPreferences("user_pref", MODE_PRIVATE)
        val regEmail = sharedPreferences.getString("registered_email", "")
        val regPassword = sharedPreferences.getString("registered_password", "")

        if (!regEmail.isNullOrEmpty()) {
            binding.edtEmail.setText(regEmail)
        }
        if (!regPassword.isNullOrEmpty()) {
            binding.edtPassword.setText(regPassword)
        }
    }
}
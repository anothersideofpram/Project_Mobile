package com.example.project_pramana

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_pramana.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.txtToLogin.setOnClickListener {
            finish()
        }

        binding.btnRegister.setOnClickListener {
            val nama = binding.edtNama.text.toString().trim()
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()
            val phone = binding.edtPhone.text.toString().trim()

            if (nama.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Semua kolom harus diisi!", Toast.LENGTH_SHORT).show()
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

            if (phone.length < 10) {
                Toast.makeText(this, "Nomor HP minimal 10 digit!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sharedPreferences = getSharedPreferences("user_pref", MODE_PRIVATE)
            sharedPreferences.edit {
                putString("registered_nama", nama)
                putString("registered_email", email)
                putString("registered_password", password)
                putString("registered_phone", phone)
                putString("nama", nama)
                putString("email", email)
                putString("password", password)
                putString("noHp", phone)
            }

            Toast.makeText(this, "Pendaftaran Berhasil! Silakan Login", Toast.LENGTH_LONG).show()

            val intent = intent
            intent.putExtra("registered_email", email)
            intent.putExtra("registered_password", password)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}
package com.example.project_pramana

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.project_pramana.databinding.FragmentProfilBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ProfilFragment : Fragment() {

    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = activity?.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val savedNama = sharedPref?.getString("nama", "")
        val regNama = sharedPref?.getString("registered_nama", "")
        val savedEmail = sharedPref?.getString("email", "Alex Chen") ?: "Alex Chen"
        val savedPhone = sharedPref?.getString("noHp", "") ?: sharedPref?.getString("registered_phone", "08123456789") ?: "08123456789"

        val displayName = when {
            !savedNama.isNullOrEmpty() -> savedNama
            !regNama.isNullOrEmpty() -> regNama
            else -> savedEmail.substringBefore("@")
        }

        binding.txtProfileName.text = displayName
        binding.txtProfilePhone.text = "$savedPhone • Verified Citizen"

        binding.btnLogoutProfile.setOnClickListener {
            context?.let { ctx ->
                MaterialAlertDialogBuilder(ctx)
                    .setTitle(getString(R.string.logout_confirm_title))
                    .setMessage(getString(R.string.logout_confirm_msg))
                    .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                        dialog.dismiss()
                        sharedPref?.edit {
                            putBoolean("isLogin", false)
                        }
                        Toast.makeText(context, "Berhasil logout", Toast.LENGTH_SHORT).show()
                        val intent = Intent(activity, HalamanLoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        activity?.finish()
                    }
                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.project_pramana

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.project_pramana.databinding.FragmentSosMapBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SosMapFragment : Fragment() {

    private var _binding: FragmentSosMapBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSosMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBigSos.setOnClickListener {
            context?.let { ctx ->
                MaterialAlertDialogBuilder(ctx)
                    .setTitle("🚨 SINYAL SOS DARURAT")
                    .setMessage("Sinyal darurat dikirimkan ke pengurus RT 04 dan relawan terdekat. Tetap tenang dan tunggu di lokasi aman.")
                    .setPositiveButton("Mengerti") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }

        binding.btnCallVolunteer.setOnClickListener {
            Toast.makeText(context, "Menghubungi Relawan Rahmad...", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
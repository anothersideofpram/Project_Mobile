package com.example.project_pramana

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.project_pramana.databinding.FragmentLaporBinding

class LaporFragment : Fragment() {

    private var _binding: FragmentLaporBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLaporBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categories = arrayOf(
            "Pilih kategori...",
            "Fasilitas Publik / Kerusakan",
            "Kebersihan / Sampah",
            "Keamanan Lingkungan",
            "Penerangan Jalan"
        )

        context?.let { ctx ->
            val adapter = ArrayAdapter(ctx, R.layout.simple_spinner_dropdown_item, categories)
            binding.spinnerKategori.adapter = adapter
        }

        binding.cardUploadFoto.setOnClickListener {
            Toast.makeText(context, "Membuka galeri untuk unggah foto...", Toast.LENGTH_SHORT).show()
        }

        binding.btnKirimLaporan.setOnClickListener {
            val desc = binding.edtDeskripsi.text.toString().trim()
            if (desc.isEmpty()) {
                Toast.makeText(context, "Mohon isi deskripsi masalah!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Laporan Anda berhasil dikirim!", Toast.LENGTH_LONG).show()
                binding.edtDeskripsi.text?.clear()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.project_pramana.Tutorial

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project_pramana.HalamanLoginActivity
import com.example.project_pramana.R
import com.example.project_pramana.databinding.FragmentHomeBinding
import com.example.project_pramana.databinding.FragmentTutorial3Binding

class Tutorial3Fragment : Fragment() {
    private var _binding: FragmentTutorial3Binding?= null
    private val binding get() =_binding!!
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTutorial3Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnLogin.setOnClickListener{
            startActivity(Intent(requireContext(), HalamanLoginActivity::class.java))
        }
    }

}
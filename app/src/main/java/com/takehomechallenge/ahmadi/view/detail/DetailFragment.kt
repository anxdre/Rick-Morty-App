package com.takehomechallenge.ahmadi.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.takehomechallenge.ahmadi.R
import com.takehomechallenge.ahmadi.data.model.Character
import com.takehomechallenge.ahmadi.databinding.FragmentDetailBinding

class DetailFragment:Fragment(R.layout.fragment_detail) {
    private var _binding:FragmentDetailBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  FragmentDetailBinding.inflate(inflater,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data: Character = arguments?.getSerializable("data") as Character

        binding?.data = data
    }
}
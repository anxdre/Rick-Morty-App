package com.takehomechallenge.ahmadi.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.takehomechallenge.ahmadi.R
import com.takehomechallenge.ahmadi.databinding.FragmentDetailBinding
import com.takehomechallenge.ahmadi.databinding.FragmentSearchBinding

class SearchFragment:Fragment(R.layout.fragment_search) {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  FragmentSearchBinding.inflate(inflater,container,false)
        return binding!!.root
    }
}
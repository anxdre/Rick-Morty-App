package com.takehomechallenge.ahmadi.view.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.takehomechallenge.ahmadi.R
import com.takehomechallenge.ahmadi.databinding.FragmentDetailBinding
import com.takehomechallenge.ahmadi.databinding.FragmentFavoriteBinding

class FavoriteFragment:Fragment(R.layout.fragment_favorite) {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  FragmentFavoriteBinding.inflate(inflater,container,false)
        return binding!!.root
    }
}
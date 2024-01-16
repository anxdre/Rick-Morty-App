package com.takehomechallenge.ahmadi.view.home

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.takehomechallenge.ahmadi.data.model.Character

interface HomeEventHandler {
    fun onScrollReachEnd(view: RecyclerView)
}
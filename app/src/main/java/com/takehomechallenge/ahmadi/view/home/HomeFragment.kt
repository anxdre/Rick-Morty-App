package com.takehomechallenge.ahmadi.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.takehomechallenge.ahmadi.R
import com.takehomechallenge.ahmadi.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { HomeViewModel() }

    private lateinit var homeAdapter: HomeItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeAdapter = HomeItemAdapter { data, position ->
            findNavController().navigate(
                R.id.action_homeFragment_to_detailFragment,
                bundleOf(Pair("data", data))
            )
        }

        with(binding) {
            adapter = homeAdapter
            rvHome.setOnScrollChangeListener { v, _, _, _, _ ->
                if (!v.canScrollVertically(1)) {
                    viewModel.fetchNextPage()
                }
            }

            srHome.setOnRefreshListener {
                viewModel.fetchRefresh()
            }

            sbHome.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
            }

            btnFavorite.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
            }
        }

        viewModel.listOfCharacter.observe(viewLifecycleOwner) {
            homeAdapter.addDataSet(it)
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            binding.srHome.isRefreshing = it
        }
    }
}
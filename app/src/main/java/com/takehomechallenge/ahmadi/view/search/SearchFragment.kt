package com.takehomechallenge.ahmadi.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.takehomechallenge.ahmadi.R
import com.takehomechallenge.ahmadi.databinding.FragmentSearchBinding
import com.takehomechallenge.ahmadi.util.shortSnackBar

class SearchFragment:Fragment(R.layout.fragment_search) {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { SearchViewModel() }

    private lateinit var searchAdapter: SearchItemAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchAdapter = SearchItemAdapter { data, _ ->
            viewModel.warnMessage.value = ""
            findNavController().navigate(
                R.id.action_searchFragment_to_detailFragment,
                bundleOf(Pair("data", data))
            )
        }

        with(binding) {
            adapter = searchAdapter
            rvHome.setOnScrollChangeListener { v, _, _, _, _ ->
                if (!v.canScrollVertically(1)) {
                    viewModel.fetchNextPage()
                }
            }

            srHome.setOnRefreshListener {
                viewModel.fetchRefresh()
            }

            sbMain.setOnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (sbMain.text.toString().isNotEmpty()) {
                        viewModel.query = sbMain.text.toString()
                        viewModel.fetchRefresh()
                    }
                    rvHome.requestFocus()
                }
                true
            }

            sbMain.requestFocus()
        }

        viewModel.listOfCharacter.observe(viewLifecycleOwner) {
            searchAdapter.addDataSet(it)
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            binding.srHome.isRefreshing = it
        }

        viewModel.warnMessage.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                shortSnackBar(requireContext(), binding.root, it)
            }
        }
    }
}
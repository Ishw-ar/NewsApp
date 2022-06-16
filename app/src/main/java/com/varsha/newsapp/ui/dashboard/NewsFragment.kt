package com.varsha.newsapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.varsha.newsapp.databinding.FragmentNewsBinding
import com.varsha.newsapp.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private val newsViewModel by viewModels<NewsViewModel>()
    private lateinit var newsAdapter: NewsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(layoutInflater, container, false)

        newsViewModel.getAllNews()

        setUpRecyclerView()
        subscribeToObservables()


        return binding.root
    }

    private fun setUpRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun subscribeToObservables() {
        newsViewModel.newsResponse.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles)

                    }

                }
                is NetworkResult.Loading -> {

                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

}
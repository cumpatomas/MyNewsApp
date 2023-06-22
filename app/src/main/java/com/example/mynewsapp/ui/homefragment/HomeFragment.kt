package com.example.mynewsapp.ui.homefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynewsapp.R
import com.example.mynewsapp.adapter.NewsAdapter
import com.example.mynewsapp.data.model.NewModel
import com.example.mynewsapp.databinding.HomefragmentBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment: Fragment() {

    private var _binding: HomefragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel = HomeFragmentViewModel()
    private val adapter = NewsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomefragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtMarquee: TextView = view.findViewById(R.id.tvSubTitle)
        txtMarquee.isSelected = true
        txtMarquee.isSingleLine = true
        initRecyclerView()
        initCollectors()
        initListeners()

    }

    private fun initRecyclerView() {
        val recyclerView =
            binding.rvNewsRecyclerView// encontramos el Recycler del xml relacionado al fragment
        recyclerView.layoutManager =
            LinearLayoutManager(this.context) // aca en contexto como no es el Main ponemos context
        recyclerView.adapter = this.adapter
    }

    private fun initCollectors() {
        lifecycleScope.launch {
            viewModel.loading.collectLatest { loading ->
                binding.progressBar.isVisible = loading
            launch {
                viewModel.newsList.collectLatest { newsList ->
                    updateList(newsList)
                }
            }.join()


            }
        }

    }

    private fun updateList(newsList: List<NewModel>) {
        adapter.setList(newsList)
    }

    private fun initListeners() {

        adapter.onItemClickListener = {url ->
            val action = HomeFragmentDirections.actionHomeFragmentToNewItemFragment(url)
            findNavController().navigate(action)
        }
    }
}
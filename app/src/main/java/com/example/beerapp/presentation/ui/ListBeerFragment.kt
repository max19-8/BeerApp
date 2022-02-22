package com.example.beerapp.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beerapp.databinding.FragmentListBeerBinding
import com.example.beerapp.presentation.adapter.*
import com.example.beerapp.presentation.model.BeerPresentationModelItem
import com.example.beerapp.presentation.viewmodel.ListBeerViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListBeerFragment : BaseFragment<FragmentListBeerBinding>() {
    private val viewModel: ListBeerViewModel by viewModel()
    private var beerAdapter: BeerListAdapter? = null
    override fun getViewBinding(): FragmentListBeerBinding =
        FragmentListBeerBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        createAdapter()
        updateAdapter()
        loadData()
        searchByName()
    }

    private fun createAdapter() {
        beerAdapter = BeerListAdapter(object : OnBeerClickListener {
            override fun onBeerClick(
                beer: BeerPresentationModelItem
            ) {
                val action = ListBeerFragmentDirections.actionListBeerFragmentToDetailFragment(
                    beer
                )
                navigate(action)
            }
        })
        binding.buttonRandom.setOnClickListener {
            navigate(ListBeerFragmentDirections.actionListBeerFragmentToDialogRandomFragment())
        }
    }

    private fun searchByName() {
        val searchEdit = binding.searchEditText
        searchEdit.addTextChangedListener {
            if (it != null) {
                searchEdit.clearFocus()
                viewModel.onQueryChange(it.toString())
            }
        }
    }

    private fun loadData() {
        lifecycleScope.launch {
            viewModel.getBeers.collectLatest {
                beerAdapter?.submitData(it)
            }
        }
    }

    private fun updateAdapter() {
        beerAdapter?.addLoadStateListener { state: CombinedLoadStates ->
            val refreshState = state.refresh
            binding.progress.isVisible = refreshState == LoadState.Loading
        }
        binding.beerRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = beerAdapter?.withLoadStateHeaderAndFooter(
                header = BeersLoaderStateAdapter{beerAdapter!!.retry()},
                footer = BeersLoaderStateAdapter{beerAdapter!!.retry()})
        }
    }
}
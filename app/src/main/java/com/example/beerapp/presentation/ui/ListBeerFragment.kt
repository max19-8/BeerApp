package com.example.beerapp.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beerapp.databinding.FragmentListBeerBinding
import com.example.beerapp.presentation.adapter.BeerListAdapter
import com.example.beerapp.presentation.adapter.BeersLoaderStateAdapter
import com.example.beerapp.presentation.adapter.OnBeerClickListener
import com.example.beerapp.presentation.viewmodel.ListBeerViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListBeerFragment : BaseFragment<FragmentListBeerBinding>(), OnBeerClickListener {
    private val viewModel: ListBeerViewModel by viewModel()
    private var beerAdapter: BeerListAdapter? = null

    override fun getViewBinding(): FragmentListBeerBinding =
        FragmentListBeerBinding.inflate(layoutInflater)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        beerAdapter = BeerListAdapter(this)
        updateAdapter()
        loadData()
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
            binding.beerRecyclerView.isVisible = refreshState != LoadState.Loading
            binding.progress.isVisible = refreshState == LoadState.Loading
            if (refreshState is LoadState.Error) {
                Snackbar.make(
                    binding.root, refreshState.error.localizedMessage ?: "",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
        binding.beerRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = beerAdapter?.withLoadStateHeaderAndFooter(
                header = BeersLoaderStateAdapter(),
                footer = BeersLoaderStateAdapter()
            )
        }
    }

    override fun onBeerClick(
        beerId: Int,
        beerName: String,
        description: String,
        strengthDrinks: Float,
        imageUrl: String,
        hydrogenIndex: Float
    ) {
        val action = ListBeerFragmentDirections.actionListBeerFragmentToDetailFragment3(
            beerId,
            beerName,
            description,
            strengthDrinks,
            imageUrl,
            hydrogenIndex
        )
        findNavController().navigate(action)
    }
}
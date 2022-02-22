package com.example.beerapp.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.ToggleButton
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beerapp.R
import com.example.beerapp.data.app.ConnectivityStatus
import com.example.beerapp.databinding.FragmentListBeerBinding
import com.example.beerapp.presentation.adapter.*
import com.example.beerapp.presentation.model.BeerPresentationModelItem
import com.example.beerapp.presentation.viewmodel.ListBeerViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListBeerFragment : BaseFragment<FragmentListBeerBinding>() {
    private val viewModel: ListBeerViewModel by viewModel()
    private var beerAdapter: BeerListAdapter? = null
    private var connect: ConnectivityStatus? = null

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
        },
            object : IsFavoriteClickListener{
                override fun changeIsFavorite(beerPresentationModelItem: BeerPresentationModelItem,isFavorite:Boolean) {
                    viewModel.addDeleteFavorite(beerPresentationModelItem,isFavorite)
                }
            }, object : CheckIsFavoriteListener {
                override fun checkIsFavorite(beerPresentationModelItem: BeerPresentationModelItem, btn: ToggleButton) {
                    lifecycleScope.launch {
                        btn.isChecked = viewModel.checkFavorite(beerPresentationModelItem.id!!)
                    }
                }
            })
        binding.buttonRandom.setOnClickListener {
            navigate(ListBeerFragmentDirections.actionListBeerFragmentToDialogRandomFragment())
        }
        clickRandom()
    }

    private fun clickRandom() = binding.buttonRandom.setOnClickListener {
        connect = ConnectivityStatus(requireContext())
        connect?.observe(viewLifecycleOwner) {
            if (it) {
                navigate(ListBeerFragmentDirections.actionListBeerFragmentToDialogRandomFragment())
            }else{
                Snackbar.make(
                    binding.root,
                    getString(R.string.check_internet_text),
                    Snackbar.LENGTH_LONG
                ).show()
            }
            connect?.removeObservers(viewLifecycleOwner)
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
    private fun loadData() =
        lifecycleScope.launch {
            viewModel.getBeers.collectLatest {
                beerAdapter?.submitData(it)
            }
        }
    private fun updateAdapter() {
        beerAdapter?.addLoadStateListener { state: CombinedLoadStates ->
            val refreshState = state.refresh
            binding.progress.isVisible = refreshState == LoadState.Loading
            if (refreshState is LoadState.Error) {
                hideContent()
            }
            binding.refreshButton.setOnClickListener {
                lifecycleScope.launch {
                    beerAdapter!!.retry()
                    if (refreshState !is LoadState.Error)
                        showContent()
                }
            }
        }
        binding.beerRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = beerAdapter?.withLoadStateHeaderAndFooter(
                header = BeersLoaderStateAdapter { beerAdapter!!.retry() },
                footer = BeersLoaderStateAdapter { beerAdapter!!.retry() })
        }
    }
    private fun hideContent() =
        with(binding) {
            textViewTitle.isVisible = false
            buttonRandom.isVisible = false
            beerRecyclerView.isVisible = false
            searchEditText.isVisible = false
            refreshButton.isVisible = true
            imageNoInternetConnection.isVisible = true
            textViewError.apply {
                isVisible = true
                text = getString(R.string.error_download_text)
            }
        }
    private fun showContent() =
        with(binding) {
            textViewTitle.isVisible = true
            buttonRandom.isVisible = true
            beerRecyclerView.isVisible = true
            searchEditText.isVisible = true
            refreshButton.isVisible = false
            imageNoInternetConnection.isVisible = false
            textViewError.isVisible = false
        }
}
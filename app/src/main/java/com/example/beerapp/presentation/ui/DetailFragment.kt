package com.example.beerapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.beerapp.R
import com.example.beerapp.databinding.FragmentDetailBinding
import com.example.beerapp.presentation.adapter.CheckIsFavoriteListener
import com.example.beerapp.presentation.model.BeerPresentationModelItem
import com.example.beerapp.presentation.viewmodel.DetailBeerViewModel
import com.example.beerapp.presentation.viewmodel.ListBeerViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailBeerViewModel by viewModel()

    override fun onStart() {
        super.onStart()
        val sheetContainer = requireView().parent as? ViewGroup ?: return
        sheetContainer.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.bind(inflater.inflate(R.layout.fragment_detail, container))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()
    }

    private fun updateUI() {
        with(binding) {
            with(args.beer) {
                textViewDetailName.text = name
                strengthBeerTextViewDetail.text =
                    context?.getString(R.string.alcohol_content_text, strengthDrinks.toString())
                hydrogenIndexTextViewDetail.text =
                    context?.getString(R.string.hydrogen_index_text, hydrogenIndex.toString())
                textViewDetailDescription.text = description
                Glide.with(requireContext())
                    .load(imageUrl)
                    .fitCenter()
                    .error(R.drawable.ic_launcher_background)
                    .into(imageViewDetail)
                binding.favoriteImageView.isVisible =  runBlocking { viewModel.checkFavorite(id!!) }
            }
        }
    }
}
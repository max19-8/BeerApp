package com.example.beerapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.beerapp.R
import com.example.beerapp.databinding.FragmentDialogRandomBinding
import com.example.beerapp.presentation.viewmodel.RandomBeerViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DialogRandomFragment : DialogFragment() {

    private val viewModel: RandomBeerViewModel by viewModel()
    private var _binding: FragmentDialogRandomBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogRandomBinding.bind(
            inflater.inflate(
                R.layout.fragment_dialog_random,
                container
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.setCanceledOnTouchOutside(true)
        getRandomBeer()
        binding.buttonExit.setOnClickListener {
            dismiss()
        }
    }

    private fun getRandomBeer() {
        viewModel.getVisibilityProgress.observe(viewLifecycleOwner) {
            binding.loadingProgressBar.isVisible = it
        }
        viewModel.getRandomBeer.observe(viewLifecycleOwner) { beer ->
            binding.textViewNameBeerPopUp.text = beer.name
            binding.textViewAlcoholContentBeerPopUp.text =
                context?.getString(R.string.alcohol_content_text, beer.strengthDrinks.toString())
            Glide.with(requireContext())
                .load(beer.imageUrl)
                .fitCenter()
                .error(R.drawable.placeholder)
                .into(binding.imageBeerPopUp)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
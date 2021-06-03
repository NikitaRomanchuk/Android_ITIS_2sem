package com.itis.weather.presentation.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.itis.weather.R
import com.itis.weather.databinding.FragmentInputCityBottomSheetBinding
import com.itis.weather.domain.common.UiState
import com.itis.weather.domain.modules.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputCityBottomSheetFragment : BottomSheetDialogFragment() {

    private val binding by viewBinding(FragmentInputCityBottomSheetBinding::bind)

    private val viewModel by viewModels<WeatherViewModels>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return FragmentInputCityBottomSheetBinding.inflate(layoutInflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.inputButton.setOnClickListener {
            viewModel.getWeatherLatLong(
                binding.inputCity.text.toString(),
                getString(R.string.api_id)
            )
        }



        viewModel.findweatherState.observe(viewLifecycleOwner, Observer { uiState ->
            when (uiState) {
                UiState.Success -> {
                    dismiss()
                }
                is UiState.Error -> Snackbar.make(view, uiState.message, Snackbar.LENGTH_LONG)
                    .show()
            }
        })
    }
}
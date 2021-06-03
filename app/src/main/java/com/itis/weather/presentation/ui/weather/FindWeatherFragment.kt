package com.itis.weather.presentation.ui.weather

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.itis.weather.R
import com.itis.weather.data.database.entity.WeatherDb
import com.itis.weather.databinding.FragmentFindWeatherBinding
import com.itis.weather.domain.modules.BaseFragment
import com.itis.weather.domain.modules.viewBinding
import com.google.android.gms.location.FusedLocationProviderClient


class FindWeatherFragment : BaseFragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val viewModel by viewModels<WeatherViewModels>()

    private val binding by viewBinding(FragmentFindWeatherBinding::bind)

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        WeatherAdapter(::onClick)
    }

    private fun onClick(id: Int) {
        findNavController().navigate(
            FindWeatherFragmentDirections.actionFindWeatherFragmentToWeatherDetailFragment(id)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentFindWeatherBinding.inflate(layoutInflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyler.adapter = adapter
        binding.recyler.setHasFixedSize(true)
        binding.recyler.layoutManager = GridLayoutManager(requireContext(), 2)

        fusedLocationClient = FusedLocationProviderClient(requireActivity())

        val permList = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        binding.findCityButton?.setOnClickListener {
            findNavController().navigate(
                FindWeatherFragmentDirections.actionFindWeatherFragmentToInputCityBottomSheetFragment()
            )
        }

        binding.findLatLongButton.setOnClickListener {
            requestPermissions(permList, 102)
        }

        observe(viewModel.allWeather, ::updateWeather)
    }

    private fun updateWeather(list: List<WeatherDb>?) {
        adapter.items = list?.toMutableList() ?: mutableListOf()
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            102 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                        viewModel.getWeatherLatLong(location?.latitude, location?.longitude, getString(
                            R.string.api_id))
                    }
                }
            }

            else -> {
                Toast.makeText(
                    requireContext(), "Permission denied to read your External storage",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return
    }

}
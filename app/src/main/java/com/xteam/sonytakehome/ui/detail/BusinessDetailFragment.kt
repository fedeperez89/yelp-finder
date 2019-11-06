package com.xteam.sonytakehome.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.xteam.sonytakehome.R
import com.xteam.sonytakehome.databinding.FragmentBusinessDetailBinding
import com.xteam.sonytakehome.util.EventObserver
import com.xteam.sonytakehome.util.setDivider
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class BusinessDetailFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<BusinessDetailViewModel> { viewModelFactory }


    private lateinit var viewDataBinding: FragmentBusinessDetailBinding
    private lateinit var imageAdapter: ImagePagerAdapter
    private lateinit var hoursAdapter: HoursAdapter

    private val args: BusinessDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_business_detail, container, false)
        viewDataBinding = FragmentBusinessDetailBinding.bind(view).apply {
            viewmodel = viewModel
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        viewModel.setBusinessId(args.businessId)

        observeCallPressedEvent()
        listenForErrors()
        setupHoursAdapter()
        setupPagerAdapter()
    }

    private fun listenForErrors() {
        viewModel.error.observe(this, EventObserver {
            Snackbar.make(viewDataBinding.coordinatorLayout, R.string.error, Snackbar.LENGTH_LONG)
                .show()
        })
    }

    private fun setupPagerAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            imageAdapter = ImagePagerAdapter(context!!, mutableListOf())
            viewDataBinding.detailViewPager.adapter = imageAdapter
        }
    }

    private fun setupHoursAdapter() {
        hoursAdapter = HoursAdapter()

        viewDataBinding.detailHoursList.apply {
            setDivider(R.drawable.divider)
            adapter = hoursAdapter
        }

    }

    private fun observeCallPressedEvent() {
        viewModel.callEvent.observe(this, EventObserver {
            callNumber(it)
        })
    }

    private fun callNumber(number: String) {
        val intent = Intent().apply {
            action = Intent.ACTION_DIAL
            data = Uri.parse("tel: $number")
        }
        startActivity(intent)
    }

}
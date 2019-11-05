package com.xteam.sonytakehome.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xteam.sonytakehome.R
import com.xteam.sonytakehome.databinding.FragmentBusinessDetailBinding
import dagger.android.support.DaggerFragment

class BusinessDetailFragment : DaggerFragment() {

    private lateinit var viewDataBinding: FragmentBusinessDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_business_detail, container, false)
        viewDataBinding = FragmentBusinessDetailBinding.bind(view).apply {
            business = null
        }

        return view
    }

}
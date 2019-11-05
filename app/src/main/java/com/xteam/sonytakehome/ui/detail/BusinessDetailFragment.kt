package com.xteam.sonytakehome.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xteam.sonytakehome.R
import dagger.android.support.DaggerFragment

class BusinessDetailFragment : DaggerFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_business_detail, container, false)
    }
}
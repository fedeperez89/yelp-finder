package com.xteam.sonytakehome.ui.search

import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.xteam.sonytakehome.R
import com.xteam.sonytakehome.databinding.FragmentSearchBinding
import com.xteam.sonytakehome.model.Business
import com.xteam.sonytakehome.util.EventObserver
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SearchFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<SearchViewModel> { viewModelFactory }

    private lateinit var viewDataBinding: FragmentSearchBinding
    private lateinit var listAdapter: BusinessAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        viewDataBinding = FragmentSearchBinding.bind(view).apply {
            viewmodel = viewModel
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        listenForSearches()
        setupListAdapter()
        setupNavigation()
    }

    private fun setupNavigation() {
        viewModel.openBusinessEvent.observe(this, EventObserver {
            openBusinessDetails(it)
        })
    }

    private fun openBusinessDetails(business: Business) {
        val action =
            SearchFragmentDirections.actionSearchFragmentToBusinessDetailFragment(
                business.id,
                business.name
            )
        findNavController().navigate(action)
    }

    private fun setupListAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            listAdapter = BusinessAdapter(viewModel)
            viewDataBinding.businessList.adapter = listAdapter
        }
    }

    private fun listenForSearches() {
        viewDataBinding.input.setOnEditorActionListener { view: View, actionId: Int, _: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                doSearch(view)
                true
            } else {
                false
            }
        }
        viewDataBinding.input.setOnKeyListener { view: View, keyCode: Int, event: KeyEvent ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                doSearch(view)
                true
            } else {
                false
            }
        }
    }

    private fun doSearch(v: View) {
        val query = viewDataBinding.input.text.toString()
        dismissKeyboard(v.windowToken)
        viewModel.setSearchQuery(query)
    }

    private fun dismissKeyboard(windowToken: IBinder) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(windowToken, 0)
    }
}
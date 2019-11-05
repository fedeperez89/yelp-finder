package com.xteam.sonytakehome.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xteam.sonytakehome.model.Business
import com.xteam.sonytakehome.repository.BusinessRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val businessRepository: BusinessRepository) :
    ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val businesses = MutableLiveData<List<Business>>(listOf())
    val businessList: LiveData<List<Business>> = businesses

    fun setSearchQuery(query: String) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            val result = businessRepository.searchBusiness(query)
            businesses.postValue(result.businesses)
            _dataLoading.postValue(false)
        }
    }
}
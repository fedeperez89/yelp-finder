package com.xteam.sonytakehome.ui.search

import androidx.lifecycle.*
import com.xteam.sonytakehome.model.Business
import com.xteam.sonytakehome.repository.BusinessRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val businessRepository: BusinessRepository) :
    ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val businesses = MediatorLiveData<List<Business>>()
    val businessList: LiveData<List<Business>> = businesses

    fun setSearchQuery(query: String) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
//            val result = businessRepository.searchBusiness(query)
            delay(4000)
            businesses.postValue(listOf())
            _dataLoading.postValue(false)
        }
    }
}
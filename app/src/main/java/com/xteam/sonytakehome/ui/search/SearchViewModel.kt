package com.xteam.sonytakehome.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xteam.sonytakehome.model.Business
import com.xteam.sonytakehome.repository.BusinessRepository
import com.xteam.sonytakehome.repository.Status
import com.xteam.sonytakehome.util.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val businessRepository: BusinessRepository) :
    ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading

    private val _businesses = MutableLiveData<List<Business>>(listOf())
    val businessList: LiveData<List<Business>>
        get() = _businesses

    private val _openBusinessEvent = MutableLiveData<Event<Business>>()
    val openBusinessEvent: LiveData<Event<Business>>
        get() = _openBusinessEvent

    private val _error = MutableLiveData<Event<String>>()
    val error: LiveData<Event<String>>
        get() = _error

    fun setSearchQuery(query: String) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            val result = businessRepository.searchBusiness(query)
            when (result.status) {
                Status.SUCCESS -> _businesses.postValue(result.data!!.businesses)
                Status.ERROR -> {
                    _businesses.postValue(listOf())
                    _error.postValue(Event("error loading"))
                }
            }

            _dataLoading.postValue(false)
        }
    }

    fun openBusiness(business: Business) {
        _openBusinessEvent.value = Event(business)
    }
}
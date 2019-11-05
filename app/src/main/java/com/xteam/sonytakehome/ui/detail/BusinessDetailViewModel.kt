package com.xteam.sonytakehome.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xteam.sonytakehome.model.BusinessDetail
import com.xteam.sonytakehome.repository.BusinessRepository
import com.xteam.sonytakehome.util.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

class BusinessDetailViewModel @Inject constructor(private val businessRepository: BusinessRepository) :
    ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _business = MutableLiveData<BusinessDetail>()
    val business: LiveData<BusinessDetail> = _business

    private val _callEvent = MutableLiveData<Event<String>>()
    val callEvent: LiveData<Event<String>> = _callEvent

    fun setBusinessId(businessId: String) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            val result = businessRepository.businessDetails(businessId)
            _business.postValue(result)
            _dataLoading.postValue(false)
        }
    }

    fun callBusiness() {
        business.value?.let {
            _callEvent.value = Event(it.phone)
        }
    }
}
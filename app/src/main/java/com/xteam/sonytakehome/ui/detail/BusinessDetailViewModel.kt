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

    private val _business = MutableLiveData<BusinessDetailPresentationObject>()
    val business: LiveData<BusinessDetailPresentationObject> = _business

    private val _callEvent = MutableLiveData<Event<String>>()
    val callEvent: LiveData<Event<String>> = _callEvent

    fun setBusinessId(businessId: String) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            val result = convertBusinessModelToUI(businessRepository.businessDetails(businessId))
            _business.postValue(result)
            _dataLoading.postValue(false)
        }
    }

    fun callBusiness() {
        business.value?.let {
            _callEvent.value = Event(it.phoneNumber)
        }
    }

    private fun convertBusinessModelToUI(model: BusinessDetail): BusinessDetailPresentationObject {
        val photos = model.photos.orEmpty()
        val category = model.categories?.firstOrNull()?.title ?: ""
        val address = model.location.let {
            "${it.address1}, ${it.city}, ${it.zip_code}"
        }
        val price = model.price ?: "???"
        return BusinessDetailPresentationObject(
            model.id,
            model.name,
            model.rating,
            price,
            model.review_count,
            photos,
            category,
            address,
            model.phone
        )
    }
}
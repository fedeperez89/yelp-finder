package com.xteam.sonytakehome.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xteam.sonytakehome.model.BusinessDetail
import com.xteam.sonytakehome.repository.BusinessRepository
import com.xteam.sonytakehome.repository.Status
import com.xteam.sonytakehome.util.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for Business Detail Screen.
 */
class BusinessDetailViewModel @Inject constructor(private val businessRepository: BusinessRepository) :
    ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading

    private val _business = MutableLiveData<BusinessDetailPresentationObject>()
    val business: LiveData<BusinessDetailPresentationObject>
        get() = _business

    private val _callEvent = MutableLiveData<Event<String>>()
    val callEvent: LiveData<Event<String>>
        get() = _callEvent

    private val _error = MutableLiveData<Event<String>>()
    val error: LiveData<Event<String>>
        get() = _error

    fun setBusinessId(businessId: String) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            val result = businessRepository.businessDetails(businessId)
            when (result.status) {
                Status.SUCCESS -> _business.postValue(convertBusinessModelToUI(result.data!!))
                Status.ERROR -> {
                    _error.postValue(Event("error loading"))
                    _business.postValue(null)
                }

            }

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
        val hours = model.hours?.firstOrNull()?.open ?: listOf()
        return BusinessDetailPresentationObject(
            model.id,
            model.name,
            model.rating,
            price,
            model.review_count,
            photos,
            category,
            address,
            model.phone,
            hours
        )
    }
}
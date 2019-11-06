package com.xteam.sonytakehome.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.xteam.sonytakehome.MainCoroutineRule
import com.xteam.sonytakehome.business
import com.xteam.sonytakehome.businessDetail
import com.xteam.sonytakehome.getOrAwaitValue
import com.xteam.sonytakehome.repository.BusinessRepository
import com.xteam.sonytakehome.repository.FakeBusinessRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class BusinessDetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: BusinessDetailViewModel

    private lateinit var businessRepository: BusinessRepository

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        businessRepository = FakeBusinessRepository(listOf(business), businessDetail)
        viewModel = BusinessDetailViewModel(businessRepository)
    }

    @Test
    fun callBusiness() {
        viewModel.setBusinessId("id")
        viewModel.callBusiness()

        assertEquals("123456", viewModel.callEvent.getOrAwaitValue().peekContent())
    }

    @Test
    fun getBusinessById() = mainCoroutineRule.runBlockingTest {

        viewModel.setBusinessId("id")

        val businessDetail = viewModel.business.getOrAwaitValue()

        val expected = BusinessDetailPresentationObject(
            "id",
            "Tony Pizza",
            4.5f,
            "$$",
            38,
            listOf("image_url"),
            "Pizza place",
            "123 fake street, Springfield, 1234",
            "123456",
            listOf()
        )
        assertEquals(expected, businessDetail)

    }

}

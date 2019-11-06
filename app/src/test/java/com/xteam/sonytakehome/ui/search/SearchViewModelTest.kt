package com.xteam.sonytakehome.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.xteam.sonytakehome.*
import com.xteam.sonytakehome.model.Business
import com.xteam.sonytakehome.repository.BusinessRepository
import com.xteam.sonytakehome.repository.FakeBusinessRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: SearchViewModel

    private lateinit var businessRepository: BusinessRepository

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        businessRepository = FakeBusinessRepository(listOf(business), businessDetail)
        viewModel = SearchViewModel(businessRepository)
    }

    @Test
    fun openDetailEvent() {
        val business: Business = mock()
        viewModel.openBusiness(business)

        assertEquals(business, viewModel.openBusinessEvent.getOrAwaitValue().peekContent())
    }

    @Test
    fun searchBusiness() = mainCoroutineRule.runBlockingTest {
        viewModel.setSearchQuery("query")

        val businessList = viewModel.businessList.getOrAwaitValue();
        assertEquals(1, businessList.size)
        assertEquals(business, businessList.first())

    }

}

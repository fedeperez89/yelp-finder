package com.xteam.sonytakehome.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.xteam.sonytakehome.getOrAwaitValue
import com.xteam.sonytakehome.mock
import com.xteam.sonytakehome.model.Business
import com.xteam.sonytakehome.repository.BusinessRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class SearchViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: SearchViewModel

    private lateinit var businessRepository: BusinessRepository

    @Before
    fun setup() {
        businessRepository = mock()
        viewModel = SearchViewModel(businessRepository)
    }

    @Test
    fun openDetailEvent() {
        val business: Business = mock()
        viewModel.openBusiness(business)

        assertEquals(viewModel.openBusinessEvent.getOrAwaitValue().peekContent(), business)
    }
}

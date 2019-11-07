package com.xteam.sonytakehome.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.xteam.sonytakehome.AndroidTestMainCoroutineRule
import com.xteam.sonytakehome.MainActivity
import com.xteam.sonytakehome.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@LargeTest
class SearchFragmentTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Rule
    @JvmField
    val taskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = AndroidTestMainCoroutineRule()

    @Before
    fun setup() {
    }

    @Test
    fun testSearch() = mainCoroutineRule.runBlockingTest {
        onView(withId(R.id.input))
            .perform(typeText("Pizza"), pressImeActionButton())

        Thread.sleep(100)
        // FIXME implement idling resources for coroutines and databinding
        onView((withText("Pizza at the Cove"))).check(matches(isDisplayed()))
        onView((withText("Pizza at the Cove"))).perform(click())

        onView(withText("$$")).check(matches(isDisplayed()))

    }

    @Test
    fun testSearchNoResults() = mainCoroutineRule.runBlockingTest {
        onView(withId(R.id.input))
            .perform(typeText("Fries"), pressImeActionButton())

        onView((withId(R.id.no_results_text))).check(matches(isDisplayed()))
    }
}
package com.cts.sample

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import com.cts.sample.CustomAssertions.Companion.hasItemCount
import com.cts.sample.ui.MainActivity
import org.junit.Rule
import org.junit.Test

class MainActivityTest{

    // This activityRule is used to launch the activity under test(In this case it is MainActivity)
    @Rule
    @JvmField
    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    /* This test method checks the number of elements in RecyclerView. */
    @Test
    fun countHerosWithViewAssertion() {
        onView(withId(R.id.recyclerview))
            .check(hasItemCount(5))
    }

    /* This test method checks if RecyclerView is displayed or not. */
    @Test
    fun ensureRecyclerViewIsPresent() {
        onView(withId(R.id.recyclerview))
            .check(matches(isDisplayed()))
    }

    /* This test method checks if RecyclerView is scrolling or not. */
    @Test
    fun ensureRecyclerViewIsScrolling() {
        onView(withId(R.id.recyclerview))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
    }

    /* This test method checks if Error textView is displayed or not. */
    @Test
    fun checkIfErrorIsPresent() {
        onView(withId(R.id.tv_no_data))
            .check(matches(isDisplayed()))
    }
}
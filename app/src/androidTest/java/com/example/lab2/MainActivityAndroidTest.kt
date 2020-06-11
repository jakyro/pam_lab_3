package com.example.lab2

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.lab2.Utils.Companion.waitFor
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityAndroidTest {

    @Test
    fun test_pager() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.pager)).inRoot(not(isDialog()))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_description() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.textView)).inRoot(not(isDialog()))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.description)))
    }

    @Test
    fun test_navigation() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.navigation)).inRoot(not(isDialog()))
            .check(matches(isDisplayed()))
            .check(matches(hasChildCount(3)))
            .check(
                matches(
                    withChild(
                        allOf(
                            withText("Top Movies"),
                            hasSibling(withText("All Movies")),
                            hasSibling(withText("Add a Movie"))
                        )
                    )
                )
            )
    }

    @Test
    fun test_pager_default_view() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.pager)).inRoot(not(isDialog()))
            .check(matches(displayedDescendant(R.string.top_week)))
    }

    @Test
    fun test_pager_swipeRight_default_view() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.pager)).inRoot(not(isDialog()))
            .check(matches(displayedDescendant(R.string.top_week)))
            .perform(swipeRight())
            .check(matches(displayedDescendant(R.string.top_week)))
    }

    @Test
    fun test_pager_swipe1_allMovies() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.pager)).inRoot(not(isDialog()))
            .check(matches(displayedDescendant(R.string.top_week)))
            .check(matches(not(displayedDescendant(R.string.all_movies))))
            .perform(swipeLeft())
            .check(matches(displayedDescendant(R.string.all_movies)))
    }

    @Test
    fun test_pager_swipe2_addAMovie() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.pager)).inRoot(not(isDialog()))
            .check(matches(displayedDescendant(R.string.top_week)))
            .check(matches(not(displayedDescendant(R.string.all_movies))))
            .perform(swipeLeft())
            .perform(waitFor(100))
            .perform(swipeLeft())
            .check(matches(displayedDescendant(R.string.add_a_movie)))
    }

    @Test
    fun test_pager_swipe3_addAMovie() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.pager)).inRoot(not(isDialog()))
            .check(matches(displayedDescendant(R.string.top_week)))
            .check(matches(not(displayedDescendant(R.string.all_movies))))
            .perform(swipeLeft())
            .perform(waitFor(100))
            .perform(swipeLeft())
            .perform(waitFor(100))
            .perform(swipeLeft())
            .check(matches(displayedDescendant(R.string.add_a_movie)))
    }

    private fun displayedDescendant(id: Int): Matcher<View> {
        return hasDescendant(allOf(withText(id), isDisplayed()))
    }
}
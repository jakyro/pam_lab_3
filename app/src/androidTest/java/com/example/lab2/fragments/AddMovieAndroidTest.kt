package com.example.lab2.fragments

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.lab2.MainActivity
import com.example.lab2.R
import com.example.lab2.Utils.Companion.waitFor
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class AddMovieAndroidTest {
    @Test
    fun test_formVisibility() {
        startFragmentAddMovie()
        onViewAndDisplayed(withId(R.id.edit_movie_name))
        onViewAndDisplayed(withId(R.id.edit_movie_category))
        onViewAndDisplayed(withId(R.id.edit_movie_thumbnail))
        onViewAndDisplayed(withId(R.id.edit_movie_year))
        onViewAndDisplayed(withId(R.id.edit_movie_length))
    }

    @Test
    fun test_formValues() {
        startFragmentAddMovie()
        writeTo(withId(R.id.edit_movie_name), "Name").check(matches(withText("Name")))
        writeTo(withId(R.id.edit_movie_category), "Category").check(matches(withText("Category")))
        writeTo(withId(R.id.edit_movie_thumbnail), "url").check(matches(withText("url")))
        writeTo(withId(R.id.edit_movie_year), "2020").check(matches(withText("2020")))
        writeTo(withId(R.id.edit_movie_length), "145").check(matches(withText("145")))
    }

    @Test
    fun test_clearForm() {
        test_formValues()
        onViewAndDisplayed(withId(R.id.clear_button)).perform(click())
        onViewAndDisplayed(withId(R.id.edit_movie_name)).check(matches(withText("")))
        onViewAndDisplayed(withId(R.id.edit_movie_category)).check(matches(withText("")))
        onViewAndDisplayed(withId(R.id.edit_movie_thumbnail)).check(matches(withText("")))
        onViewAndDisplayed(withId(R.id.edit_movie_year)).check(matches(withText("")))
        onViewAndDisplayed(withId(R.id.edit_movie_length)).check(matches(withText("")))
    }

    private fun writeTo(cmp: Matcher<View>, value: String): ViewInteraction {
        return onViewAndDisplayed(cmp).perform(replaceText(value))
    }

    private fun onViewAndDisplayed(cmp: Matcher<View>): ViewInteraction {
        return onView(cmp).inRoot(not(isDialog())).check(matches(isDisplayed()))
    }

    private fun startFragmentAddMovie() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onViewAndDisplayed(withId(R.id.pager))
            .perform(swipeLeft())
            .perform(waitFor(100))
            .perform(swipeLeft())
    }
}
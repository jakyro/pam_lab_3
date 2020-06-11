package com.example.lab2

import android.content.Intent
import android.view.View
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.lab2.model.MovieModel
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ViewMovieAndroidTest {
    companion object {
        var movie = MovieModel(
            "1",
            "Parasite",
            "Comedy, Drama, Thriller",
            "https://m.media-amazon.com/images/M/MV5BYWZjMjk3ZTItODQ2ZC00NTY5LWE0ZDYtZTI3MjcwN2Q5NTVkXkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_SY1000_CR0,0,674,1000_AL_.jpg",
            "2019",
            "132",
            "6.4815"
        )
        var intent: Intent =
            Intent(
                ApplicationProvider.getApplicationContext(),
                ViewMovie::class.java
            ).putExtra("id", "1")
    }

    @get: Rule
    val activityRule = ActivityScenarioRule<ViewMovie>(intent)

    @Test
    fun test_title() {
        onViewAndDisplayed(withText(R.string.movie_details))
    }

    @Test
    fun test_movieData() {
        onViewAndDisplayed(withId(R.id.imageView))
        onViewAndDisplayed(withId(R.id.movieName))
            .check(matches(withText(movie.name)))

        onViewAndDisplayed(withId(R.id.movieCategory))
            .check(matches(withText(movie.category)))
    }

    @Test
    fun test_spinner() {
        onViewAndDisplayed(withId(R.id.spinner))
            .perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("10")))
            .perform(click())
        onView(withId(R.id.spinner)).check(matches(withSpinnerText(containsString("10"))))
    }

    @Test
    fun test_setRating_button() {
        onViewAndDisplayed(withId(R.id.set_rating))
    }

    private fun onViewAndDisplayed(cmp: Matcher<View>): ViewInteraction {
        return onView(cmp).inRoot(not(isDialog())).check(matches(isDisplayed()))
    }
}
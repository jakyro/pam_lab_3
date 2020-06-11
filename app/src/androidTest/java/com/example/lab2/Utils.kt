package com.example.lab2

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

class Utils {
    companion object {
        fun waitFor(delay: Long): ViewAction? {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View> {
                    return ViewMatchers.isDisplayed()
                }

                override fun getDescription(): String {
                    return "wait for " + delay + "milliseconds"
                }

                override fun perform(uiController: UiController, view: View?) {
                    uiController.loopMainThreadForAtLeast(delay)
                }
            }
        }
    }
}
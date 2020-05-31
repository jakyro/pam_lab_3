package com.example.lab2

import com.example.lab2.common.Utils
import com.example.lab2.common.Utils.Companion.EMPTY_RATING
import org.junit.Test

class UtilsTest {

    @Test
    fun textInstance() {
        assert(Utils() != null)
    }

    @Test
    fun testRenderRating() {
        assert(Utils.renderRating(null) == EMPTY_RATING)
        assert(Utils.renderRating("") == EMPTY_RATING)
        assert(Utils.renderRating("0") == "0.0")
        assert(Utils.renderRating("0.5") == "0.5")
        assert(Utils.renderRating("1") == "1.0")
        assert(Utils.renderRating("1.55") == "1.6")
        assert(Utils.renderRating("1.50006") == "1.5")
    }
}
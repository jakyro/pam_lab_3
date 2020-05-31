package com.example.lab2.common

import java.lang.Exception
import java.math.RoundingMode

class Utils {
    companion object {
        const val EMPTY_RATING = "N / A"

        fun renderRating(rating: String?): String {
            return try {
                rating?.toBigDecimal()?.setScale(1, RoundingMode.HALF_EVEN)?.toPlainString()
                    ?: EMPTY_RATING
            } catch (e: Exception) {
                EMPTY_RATING
            }
        }
    }
}
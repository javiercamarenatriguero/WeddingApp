package com.akole.weddingapp.ui.utils

import org.junit.Assert
import org.junit.Test

class StringExtTest {

    @Test
    fun `GIVEN a string WHEN upperAsTitle is called THEN returns a first capital letter string` () {
        Assert.assertEquals(TITLE_OUTPUT_1, TITLE_INPUT_1.upperAsTitle())
        Assert.assertEquals(TITLE_OUTPUT_2, TITLE_INPUT_2.upperAsTitle())
    }

    @Test
    fun `GIVEN an empty String WHEN upperAsTitle is called THEN returns an empty string` () {
        Assert.assertEquals("", "".upperAsTitle())
    }

    companion object {
        private const val TITLE_INPUT_1 = "this is an example"
        private const val TITLE_INPUT_2 = "today is the future"

        private const val TITLE_OUTPUT_1 = "This is an example"
        private const val TITLE_OUTPUT_2 = "Today is the future"
    }
}
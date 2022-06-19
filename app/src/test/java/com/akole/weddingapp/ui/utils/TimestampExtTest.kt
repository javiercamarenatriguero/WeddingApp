package com.akole.weddingapp.ui.utils

import org.junit.Assert
import org.junit.Test

class TimestampExtTest {

    @Test
    internal fun `GIVEN a timestamp WHEN getDaysLeft is called THEN it returns the quantity of days` () {
        Assert.assertEquals(0, ONE_HOUR_MILLISECONDS.toLong().getDaysLeft())
        Assert.assertEquals(3, THREE_DAYS_TIMESTAMP.toLong().getDaysLeft())
        Assert.assertEquals(10, TEN_DAYS_TIMESTAMP.toLong().getDaysLeft())
    }

    @Test
    internal fun `GIVEN a timestamp WHEN getHoursLeft is called THEN it returns the quantity of hours` () {
        Assert.assertEquals(0, THIRTY_MINUTES_MILLISECONDS.toLong().getHoursLeft())
        Assert.assertEquals(1, ONE_HOUR_MILLISECONDS.toLong().getHoursLeft())
        Assert.assertEquals(0, THREE_DAYS_TIMESTAMP.toLong().getHoursLeft())
        Assert.assertEquals(0, TEN_DAYS_TIMESTAMP.toLong().getHoursLeft())
        Assert.assertEquals(1, (TEN_DAYS_TIMESTAMP + ONE_HOUR_MILLISECONDS).toLong().getHoursLeft())
    }

    @Test
    internal fun `GIVEN a timestamp WHEN getMinutesLeft is called THEN it returns the quantity of minutes` () {
        Assert.assertEquals(30, THIRTY_MINUTES_MILLISECONDS.toLong().getMinutesLeft())
        Assert.assertEquals(0, ONE_HOUR_MILLISECONDS.toLong().getMinutesLeft())
        Assert.assertEquals(0, THREE_DAYS_TIMESTAMP.toLong().getMinutesLeft())
        Assert.assertEquals(0, TEN_DAYS_TIMESTAMP.toLong().getMinutesLeft())
        Assert.assertEquals(30, (TEN_DAYS_TIMESTAMP + THIRTY_MINUTES_MILLISECONDS).toLong().getMinutesLeft())
    }

    @Test
    internal fun `GIVEN a timestamp WHEN the format string is required THEN return the correct output` () {
        Assert.assertEquals(DATETIME_OUTPUT, DATETIME_TIMESTAMP.formatTimestamp(DATETIME_FORMAT))
        Assert.assertEquals(DATE_OUTPUT, DATETIME_TIMESTAMP.formatTimestamp(DATE_FORMAT))
    }

    @Test
    internal fun `GIVEN a timestamp WHEN getSecondsLeft is called THEN it returns the quantity of seconds` () {
        Assert.assertEquals(30, THIRTY_SECONDS_MILLISECONDS.toLong().getSecondsLeft())
        Assert.assertEquals(0, ONE_MINUTE_MILLISECONDS.toLong().getSecondsLeft())
        Assert.assertEquals(0, ONE_HOUR_MILLISECONDS.toLong().getSecondsLeft())
        Assert.assertEquals(0, THREE_DAYS_TIMESTAMP.toLong().getSecondsLeft())
        Assert.assertEquals(0, TEN_DAYS_TIMESTAMP.toLong().getSecondsLeft())
        Assert.assertEquals(30, (TEN_DAYS_TIMESTAMP + THIRTY_SECONDS_MILLISECONDS).toLong().getSecondsLeft())
    }

    companion object {
        private const val THIRTY_SECONDS_MILLISECONDS = 30_000
        private const val ONE_MINUTE_MILLISECONDS = THIRTY_SECONDS_MILLISECONDS * 2
        private const val THIRTY_MINUTES_MILLISECONDS = 1_800_000
        private const val ONE_HOUR_MILLISECONDS = 3_600_000
        private const val THREE_DAYS_TIMESTAMP = ONE_HOUR_MILLISECONDS * 3 * 24
        private const val TEN_DAYS_TIMESTAMP = ONE_HOUR_MILLISECONDS * 10 * 24

        private const val DATETIME_FORMAT = "HH'h'mm - dd.MM.YYYY"
        private const val DATE_FORMAT = "dd.MM.YYYY"
        private const val DATETIME_TIMESTAMP = 1708367000000
        private const val DATETIME_OUTPUT = "19h23 - 19.02.2024"
        private const val DATE_OUTPUT = "19.02.2024"
    }
}
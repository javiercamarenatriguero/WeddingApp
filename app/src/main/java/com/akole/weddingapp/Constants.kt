package com.akole.weddingapp

object Constants {
    // Random date
    internal const val WEDDING_TIMESTAMP = 1708367000000
    // 12 hours before wedding in order to start calendar notification
    internal const val CALENDAR_NOTIFICATION_START_TIMESTAMP = WEDDING_TIMESTAMP - 7200000
    // Photo service available timestamp (default 0 in order to be available)
    internal const val PHOTO_AVAILABLE_TIMESTAMP = 0
    // Wedding date string format
    internal const val DATETIME_FORMAT = "HH'h'mm - dd.MM.YYYY"
}
package com.akole.weddingapp.ui.utils

import java.util.*

fun String.upperAsTitle(): String? {
    return this
        .lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}
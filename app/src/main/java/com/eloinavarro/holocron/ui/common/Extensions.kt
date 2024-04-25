package com.eloinavarro.holocron.ui.common

import com.eloinavarro.holocron.domain.SWCharacter

fun List<SWCharacter>.sortByNameCaseInsensitive()
    = this.sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { character -> character.name })

fun Int.toDate(): String =
    if(this < 0) {
        "$this BBY"
    } else {
        "$this ABY"
    }

fun String.toHeight(): String {
    val value = this.toFloatOrNull()
    value?.let {
        return String.format("%.2f m", value/100)
    }
    return ""
}

fun String.toWeight(): String {
    val value = this.toIntOrNull()
    value?.let {
        return String.format("%d Kg", value)
    }
    return ""
}
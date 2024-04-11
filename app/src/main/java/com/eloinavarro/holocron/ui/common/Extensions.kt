package com.eloinavarro.holocron.ui.common

import com.eloinavarro.holocron.domain.SWCharacter

fun List<SWCharacter>.sortByNameCaseInsensitive()
    = this.sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { character -> character.name })
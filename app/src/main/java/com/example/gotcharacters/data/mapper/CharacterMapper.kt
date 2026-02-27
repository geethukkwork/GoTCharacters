package com.example.gotcharacters.data.mapper

import com.example.gotcharacters.core.util.UiText
import com.example.gotcharacters.data.remote.dto.CharacterDto
import com.example.gotcharacters.domain.model.Character

fun CharacterDto.toDomain(index: Int): Character {
    val resolvedName = name?.takeIf { it.isNotBlank() } ?: UiText.Unknown
    val resolvedCulture = culture?.takeIf { it.isNotBlank() } ?: UiText.NotAvailable
    val resolvedBorn = born?.takeIf { it.isNotBlank() } ?: UiText.NotAvailable
    val resolvedDied = died?.takeIf { it.isNotBlank() } ?: UiText.NotAvailable

    val resolvedTitle = titles.orEmpty()
        .firstOrNull { it.isNotBlank() }
        ?: UiText.NotAvailable

    val resolvedActor = playedBy.orEmpty()
        .firstOrNull { it.isNotBlank() }
        ?: UiText.NotAvailable

    val parsedSeasons = tvSeries.orEmpty()
        .mapNotNull { season ->
            season.removePrefix("Season ").trim().toIntOrNull()
        }
        .distinct()
        .sorted()

    return Character(
        localId = index,
        stableKey = "character_${index}_$resolvedName",
        fullName = resolvedName,
        culture = resolvedCulture,
        born = resolvedBorn,
        died = resolvedDied,
        title = resolvedTitle,
        actor = resolvedActor,
        aliases = aliases.orEmpty().filter { it.isNotBlank() },
        seasons = parsedSeasons
    )
}
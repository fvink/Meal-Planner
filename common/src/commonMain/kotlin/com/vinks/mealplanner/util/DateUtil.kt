package com.vinks.mealplanner.util

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun LocalDate.isBefore(other: LocalDate) = this < other

fun LocalDate.isAfter(other: LocalDate) = this > other

fun Instant.toLocalDate() = this.toLocalDateTime(TimeZone.currentSystemDefault()).date

val currentDate = Clock.System.now().toLocalDate()
val currentDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
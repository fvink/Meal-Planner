package com.vinks.mealplanner.domain.model

data class Quantity(
    val amount: Double,
    val unit: MeasurementUnit
) {
    override fun toString(): String = "$amount ${unit.abbreviation}"
}
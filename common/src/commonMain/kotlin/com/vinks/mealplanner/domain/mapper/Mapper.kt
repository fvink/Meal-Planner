package com.vinks.mealplanner.domain.mapper

interface Mapper<I, O> {
    fun map(input: I): O
    fun mapList(input: List<I>): List<O> = input.map { element -> map(element) }
}
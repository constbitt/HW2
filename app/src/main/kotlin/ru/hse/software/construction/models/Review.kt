package ru.hse.software.construction.models

import kotlinx.serialization.Serializable

@Serializable
data class Review(val dish: Dish, val grade: Int, val comment: String)
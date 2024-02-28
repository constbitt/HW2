package ru.hse.software.construction.models

import kotlinx.serialization.Serializable

@Serializable
data class Dish(val name: String, val price: Double, val preparationTime: Int, var quantity: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Dish) return false
        return name == other.name && price == other.price
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + price.hashCode()
        return result
    }
}
package ru.hse.software.construction.repository

import kotlinx.serialization.builtins.serializer
import ru.hse.software.construction.models.Dish
import ru.hse.software.construction.models.Order
import ru.hse.software.construction.models.OrderStatus
import ru.hse.software.construction.models.User

object DataManager {
    val menu = mutableListOf<Dish>()
    val orders = mutableListOf<Order>()
    val admins = mutableListOf<User>()
    val visitors = mutableListOf<User>()
    var totalRevenue: Double = 0.0

    fun loadEntities(fileLocation: String) {
        menu.addAll(RestaurantRepository().loadFromFile("$fileLocation/menu.json", Dish.serializer()))
        admins.addAll(RestaurantRepository().loadFromFile("$fileLocation/admins.json", User.serializer()))
        visitors.addAll(RestaurantRepository().loadFromFile("$fileLocation/visitors.json", User.serializer()))
        orders.addAll(RestaurantRepository().loadFromFile("$fileLocation/orders.json", Order.serializer()))
        totalRevenue = RestaurantRepository().loadFromFile("$fileLocation/revenue.json", Double.serializer()).firstOrNull()?: 0.0
    }

    fun saveEntities(fileLocation: String) {
        RestaurantRepository().saveToFile("$fileLocation/menu.json", menu, Dish.serializer())
        RestaurantRepository().saveToFile("$fileLocation/admins.json", admins, User.serializer())
        RestaurantRepository().saveToFile("$fileLocation/visitors.json", visitors, User.serializer())
        for (order in orders) {
            if (order.status != OrderStatus.COMPLETED) {
                order.status = OrderStatus.CANCELLED
            }
        }
        RestaurantRepository().saveToFile("$fileLocation/orders.json", orders, Order.serializer())
        RestaurantRepository().saveToFile("$fileLocation/revenue.json", listOf(totalRevenue), Double.serializer())
    }
}
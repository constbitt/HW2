package ru.hse.software.construction.repository

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.io.File

class RestaurantRepository {
    fun <T : Any> loadFromFile(fileName: String, serializer: KSerializer<T>): List<T> {
        val file = File(fileName)
        return if (file.exists()) {
            try {
                Json.decodeFromString(ListSerializer(serializer), file.readText())
            } catch (e: Exception) {
                println("Writing from file went wrong. No data from $fileName will be written.")
                emptyList()
            }
        } else {
            emptyList()
        }
    }

    fun <T : Any> saveToFile(fileName: String, data: List<T>, serializer: KSerializer<T>) {
        try {
            val file = File(fileName)
            val json = Json.encodeToString(ListSerializer(serializer), data)
            file.writeText(json)
        } catch (e: Exception) {
            println("Writing to file went wrong. No data will be written to $fileName.")
        }
    }
}
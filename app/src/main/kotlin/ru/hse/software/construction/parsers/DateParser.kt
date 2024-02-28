package ru.hse.software.construction.parsers

import java.time.LocalDate
import java.util.*

class DateParser : Parser<LocalDate> {
    override fun parse(): LocalDate {
        val scanner = Scanner(System.`in`)
        while (true) {
            print("\tInput date (yyyy MM dd): ")
            try {
                val year = scanner.nextInt()
                val month = scanner.nextInt()
                val day = scanner.nextInt()
                return LocalDate.of(year, month, day)
            } catch (e: Exception) {
                println("Invalid date format, please try again.")
                scanner.nextLine()
            }
        }
    }
}
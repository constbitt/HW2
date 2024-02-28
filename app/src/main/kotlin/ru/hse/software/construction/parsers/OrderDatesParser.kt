package ru.hse.software.construction.parsers

import java.time.LocalDate

class OrderDatesParser: Parser<Pair<LocalDate, LocalDate>> {
    override fun parse(): Pair<LocalDate, LocalDate> {
        println("\tStart date")
        val startDate = DateParser().parse()
        println("\tEnd date")
        val endDate = DateParser().parse()
        return Pair(startDate, endDate)
    }
}
package ru.hse.software.construction.admin.commands

import ru.hse.software.construction.`interface`.output.AverageRatingByDish
import ru.hse.software.construction.`interface`.output.OrderStatistics
import ru.hse.software.construction.`interface`.output.PopularityRankingByDish
import ru.hse.software.construction.parsers.OrderDatesParser
import ru.hse.software.construction.parsers.StatisticsTypeParser


class StatisticsCommand : AdminCommand {
    override fun execute() {
        while (true) {
            val parsedValue = StatisticsTypeParser().parse()
            when (parsedValue) {
                1 -> AverageRatingByDish().output()
                2 -> PopularityRankingByDish().output()
                3 -> {
                    val dates = OrderDatesParser().parse()
                    OrderStatistics(dates.first, dates.second).output()
                }
                4 -> {
                    return
                }
                else -> {return}
            }
        }
    }
}
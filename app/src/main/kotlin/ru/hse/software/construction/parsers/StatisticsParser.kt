package ru.hse.software.construction.parsers

import java.util.*

class StatisticsTypeParser: Parser<Int> {
    override fun parse(): Int {
        val scanner = Scanner(System.`in`)
        var number: Int
        print("Select statistics:\n\t1. Dish rating\n\t2. Dish popularity\n\t3. Order statistics\n\t4. Back\n")
        do {
            print("Input a number from 1 to 4: ")
            while (!scanner.hasNextInt()) {
                print("Please, input a number from 1 to 4: ")
                scanner.next()
            }
            number = scanner.nextInt()
        } while (number !in 1..4)
        return number
    }
}
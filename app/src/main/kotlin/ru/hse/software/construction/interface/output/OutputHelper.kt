package ru.hse.software.construction.`interface`.output

import java.util.*

class OutputHelper {
    fun pressKeyToContinue() {
        println("Press enter to continue...")
        Scanner(System.`in`).nextLine()
    }
}
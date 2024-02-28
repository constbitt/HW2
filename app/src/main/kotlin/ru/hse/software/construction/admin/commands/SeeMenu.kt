package ru.hse.software.construction.admin.commands

import ru.hse.software.construction.`interface`.output.MenuPage
import ru.hse.software.construction.`interface`.output.OutputHelper

class SeeMenuCommand : AdminCommand {
    override fun execute() {
        MenuPage().load()
        OutputHelper().pressKeyToContinue()
    }
}
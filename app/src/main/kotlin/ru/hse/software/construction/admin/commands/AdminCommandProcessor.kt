package ru.hse.software.construction.admin.commands

interface Command {
    fun execute()
}

interface AdminCommand : Command {
    override fun execute()
}

class AdminCommandProcessor {
    fun processCommand(adminCommand: AdminCommand): AdminCommandProcessor = apply {
        adminCommand.execute()
    }
}
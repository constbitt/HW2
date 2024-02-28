package ru.hse.software.construction.repository

import java.io.File

interface FolderSelectionStrategy {
    fun selectFolder(defaultFolder: String): String
}

class FolderSelector(private val strategy: FolderSelectionStrategy, private val defaultFolder: String) {
    fun selectFolder(): String {
        return strategy.selectFolder(defaultFolder)
    }
}

class KeyboardInputStrategy : FolderSelectionStrategy {
    override fun selectFolder(defaultFolder: String): String {
        do {
            println("Enter the path to the folder:")
            val input = readlnOrNull().orEmpty().trim()
            if (File(input).isDirectory) {
                return input
            } else {
                println("The specified folder does not exist.")
                print("Do you want to create this folder or use the default one? \n\t" +
                        "1. Create\n\t2. Use default\nChoose an option: ")
                when (readlnOrNull()?.toIntOrNull()) {
                    1 -> {}
                    2 -> {
                        return DefaultFolderStrategy().selectFolder(defaultFolder)
                    }
                    else -> println("Invalid option. Please try again.")
                }
            }
        } while (true)
    }
}

class DefaultFolderStrategy : FolderSelectionStrategy {
    override fun selectFolder(defaultFolder: String): String {
        val defaultDir = File(defaultFolder)
        if (!defaultDir.isDirectory) {
            defaultDir.mkdirs()
            println("Default folder created due to deletion.")
        }
        return defaultFolder
    }
}

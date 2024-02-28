package ru.hse.software.construction.parsers

import ru.hse.software.construction.repository.DefaultFolderStrategy
import ru.hse.software.construction.repository.FolderSelector
import ru.hse.software.construction.repository.KeyboardInputStrategy

class FolderParser(private val defaultFolder: String): Parser<String> {
    private val keyboardStrategy = KeyboardInputStrategy()
    private val defaultFolderStrategy = DefaultFolderStrategy()
    private val folderSelector = FolderSelector(keyboardStrategy, defaultFolder)

    override fun parse(): String {
        while (true) {
            println("Folder for saving/loading data:\n\t1. Input manually\n\t2. Use default\n\t3. Exit")
            print("Choose an option: ")
            when (readlnOrNull()) {
                "1" -> {
                    val selectedFolder = folderSelector.selectFolder()
                    println("Selected folder: $selectedFolder")
                    return selectedFolder
                }
                "2" -> {
                    val folder = defaultFolderStrategy.selectFolder(defaultFolder)
                    println("Using default folder: $folder")
                    return folder
                }
                "3" -> {
                    println("Exiting...")
                    return ""
                }
                else -> println("Invalid option. Please try again.")
            }
        }
    }
}


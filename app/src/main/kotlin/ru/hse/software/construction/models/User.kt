package ru.hse.software.construction.models

import kotlinx.serialization.Serializable

@Serializable
data class User(val username: String, val password: String)

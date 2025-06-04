package com.example.magat_ponce_finals.data

/**
 * Data class representing the TableSample model from the Django backend.
 * This class is used to serialize and deserialize data when communicating
 * with the Django REST API.
 *
 * @property name The full name of the person.
 * @property age The age of the person.
 */
data class TableSample(
    val name: String,
    val age: Int
)

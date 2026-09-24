package com.nelson.expensetracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val amount: Double,
    val category: String,
    val currency: String,
    val type: String, // "Gasto" o "Ingreso"
    val imageUri: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)

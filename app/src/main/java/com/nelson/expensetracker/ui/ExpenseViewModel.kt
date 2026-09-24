package com.nelson.expensetracker.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nelson.expensetracker.data.AppDatabase
import com.nelson.expensetracker.data.Expense
import com.nelson.expensetracker.data.ExpenseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ExpenseRepository =
        ExpenseRepository(AppDatabase.getInstance(application).expenseDao())

    val expenses = repository.expenses.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    fun addExpense(
        title: String,
        amount: Double,
        category: String,
        currency: String,
        type: String,
        imageUri: String?
    ) {
        viewModelScope.launch {
            val expense = Expense(
                title = title,
                amount = amount,
                category = category,
                currency = currency,
                type = type,
                imageUri = imageUri
            )
            repository.addExpense(expense)
        }
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            repository.deleteExpense(expense)
        }
    }
}

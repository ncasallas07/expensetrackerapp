package com.nelson.expensetracker.data

class ExpenseRepository(private val dao: ExpenseDao) {

    val expenses = dao.getAllExpenses()

    suspend fun addExpense(expense: Expense) {
        dao.insert(expense)
    }

    suspend fun deleteExpense(expense: Expense) {
        dao.delete(expense)
    }
}

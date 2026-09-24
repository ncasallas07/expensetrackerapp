package com.nelson.expensetracker.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.nelson.expensetracker.databinding.ActivityStatsBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StatsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStatsBinding
    private val viewModel: ExpenseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        lifecycleScope.launch {
            viewModel.expenses.collectLatest { list ->
                val totalIncome = list.filter { it.type == "Ingreso" }.sumOf { it.amount }
                val totalExpense = list.filter { it.type == "Gasto" }.sumOf { it.amount }
                val balance = totalIncome - totalExpense

                binding.tvIncome.text = "Ingresos: $totalIncome"
                binding.tvExpense.text = "Gastos: $totalExpense"
                binding.tvBalance.text = "Balance: $balance"

                binding.statsBar.setData(
                    income = totalIncome.toFloat(),
                    expense = totalExpense.toFloat()
                )
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}

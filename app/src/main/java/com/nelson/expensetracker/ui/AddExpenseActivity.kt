package com.nelson.expensetracker.ui

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.nelson.expensetracker.databinding.ActivityAddExpenseBinding

class AddExpenseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddExpenseBinding
    private val viewModel: ExpenseViewModel by viewModels()

    private var selectedImageUri: Uri? = null

    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
        if (uri != null) {
            binding.ivInvoice.visibility = View.VISIBLE
            binding.ivInvoice.setImageURI(uri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val categories = listOf("Comida", "Transporte", "Servicios", "Ocio", "Otro")
        val currencies = listOf("COP", "USD", "EUR")
        val types = listOf("Gasto", "Ingreso")

        binding.spCategory.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)

        binding.spCurrency.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, currencies)

        binding.spType.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, types)

        binding.btnSelectImage.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        binding.btnSave.setOnClickListener {
            saveExpense()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun saveExpense() {
        val title = binding.etTitle.text.toString().trim()
        val amountStr = binding.etAmount.text.toString().trim()
        val category = binding.spCategory.selectedItem.toString()
        val currency = binding.spCurrency.selectedItem.toString()
        val type = binding.spType.selectedItem.toString()

        if (title.isEmpty() || amountStr.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val amount = amountStr.toDoubleOrNull()
        if (amount == null) {
            Toast.makeText(this, "Monto inválido", Toast.LENGTH_SHORT).show()
            return
        }

        viewModel.addExpense(
            title = title,
            amount = amount,
            category = category,
            currency = currency,
            type = type,
            imageUri = selectedImageUri?.toString()
        )
        Toast.makeText(this, "Movimiento guardado", Toast.LENGTH_SHORT).show()
        finish()
    }
}

package com.nelson.expensetracker.ui

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nelson.expensetracker.data.Expense
import com.nelson.expensetracker.databinding.ItemExpenseBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ExpenseAdapter(
    private var items: List<Expense>,
    private val onLongClick: (Expense) -> Unit
) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    private val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

    fun submitList(newItems: List<Expense>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val binding = ItemExpenseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ExpenseViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ExpenseViewHolder(
        private val binding: ItemExpenseBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(expense: Expense) {
            binding.tvTitle.text = expense.title
            binding.tvAmount.text = "${expense.amount} ${expense.currency}"
            binding.tvCategory.text = expense.category
            binding.tvType.text = expense.type
            binding.tvDate.text = dateFormat.format(Date(expense.timestamp))

            val uriStr = expense.imageUri
            if (!uriStr.isNullOrEmpty()) {
                binding.ivInvoice.visibility = View.VISIBLE
                binding.ivInvoice.setImageURI(Uri.parse(uriStr))
            } else {
                binding.ivInvoice.visibility = View.GONE
            }

            binding.root.setOnLongClickListener {
                onLongClick(expense)
                true
            }
        }
    }
}

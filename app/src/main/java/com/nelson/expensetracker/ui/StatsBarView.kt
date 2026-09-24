package com.nelson.expensetracker.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.max

class StatsBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val incomePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#4CAF50") // verde
    }
    private val expensePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#F44336") // rojo
    }

    private var income: Float = 0f
    private var expense: Float = 0f

    fun setData(income: Float, expense: Float) {
        this.income = income
        this.expense = expense
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val w = width.toFloat()
        val h = height.toFloat()
        if (w <= 0f || h <= 0f) return

        val maxValue = max(max(income, expense), 1f)
        val barWidth = w / 4f
        val space = w / 8f
        val bottom = h * 0.9f
        val maxBarHeight = h * 0.7f

        val incomeHeight = (income / maxValue) * maxBarHeight
        val expenseHeight = (expense / maxValue) * maxBarHeight

        // barra ingresos
        val incomeLeft = space
        val incomeRight = incomeLeft + barWidth
        canvas.drawRect(
            incomeLeft,
            bottom - incomeHeight,
            incomeRight,
            bottom,
            incomePaint
        )

        // barra gastos
        val expenseRight = w - space
        val expenseLeft = expenseRight - barWidth
        canvas.drawRect(
            expenseLeft,
            bottom - expenseHeight,
            expenseRight,
            bottom,
            expensePaint
        )
    }
}

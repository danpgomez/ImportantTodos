package com.example.importanttodos.util

import android.graphics.Color

object ColorProvider {
    private val colors = listOf(
        Color.rgb(128, 255, 219),
        Color.rgb(114, 239, 221),
        Color.rgb(100, 223, 223),
        Color.rgb(86, 207, 225),
        Color.rgb(72, 191, 227),
        Color.rgb(78, 168, 222),
        Color.rgb(83, 144, 217),
        Color.rgb(94, 96, 206),
        Color.rgb(105, 48, 195),
        Color.rgb(116, 0, 184),
    )

    fun getColorResourceId(position: Int) : Int {
        return colors[position % colors.size]
    }
}

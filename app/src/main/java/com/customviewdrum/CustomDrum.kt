package com.customviewdrum

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.lang.Math.cos
import java.lang.Math.sin

class CustomDrum @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val colors = arrayOf(
        Color.RED, Color.parseColor("#FC6600"), Color.YELLOW ,Color.GREEN, Color.parseColor("#42AAFF"),
        Color.BLUE, Color.parseColor("#9400D3")
    )

    private var rotationAngle = 0f

    private val paint = Paint()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)



        val centerX = width / 2f
        val centerY = height / 2f
        val radius = minOf(centerX, centerY)

        val sectors = 360f / colors.size


        val startAngle = rotationAngle % 360 - 13
        for (i in 0 until colors.size) {
            val angle = startAngle + i * sectors
            val sectorPaint = paint.apply {
                color = colors[i]
                style = Paint.Style.FILL
            }
            canvas.drawArc(
                centerX - radius,
                centerY - radius,
                centerX + radius,
                centerY + radius,
                angle,
                sectors,
                true,
                sectorPaint
            )
        }
        canvas.save()
        canvas.rotate(rotationAngle, centerX, centerY)
        canvas.restore()
    }

    fun rotate(): Int {
        val randomRotation = (3..7).random() * 1000
        var prevRotationAngle = rotationAngle % 360
        rotationAngle = (0..360).random().toFloat() + prevRotationAngle
        animate().rotationBy(rotationAngle).apply {
            duration = randomRotation.toLong()
            start()
        }
        if((rotationAngle + prevRotationAngle - 13) % 360 in 0.0..51.0){
            println("цвет оранжевый")
            return 1
        } else if ((rotationAngle + prevRotationAngle- 13) % 360 > 51 && (rotationAngle + prevRotationAngle- 13) % 360 <= 102){
            println("цвет красный")
            return 2
        } else if((rotationAngle + prevRotationAngle- 13) % 360 > 102 && (rotationAngle + prevRotationAngle- 13) % 360 <= 153){
            println("цвет фиолетовый")
            return 3
        } else if((rotationAngle + prevRotationAngle- 13) % 360 > 153 && (rotationAngle + prevRotationAngle- 13) % 360 <= 204){
            println("цвет синий")
            return 4
        }else if((rotationAngle + prevRotationAngle- 13) % 360 > 204 && (rotationAngle + prevRotationAngle- 13) % 360 <= 255){
            println("цвет голубой")
            return 5
        }else if((rotationAngle + prevRotationAngle- 13) % 360 > 255 && (rotationAngle + prevRotationAngle- 13) % 360 <= 306){
            println("цвет зеленый")
            return 6
        } else if((rotationAngle + prevRotationAngle- 13) % 360 > 306 && (rotationAngle + prevRotationAngle- 13) % 360 <= 360){
            println("цвет желтый")
            return 7
        } else {
            return 0
        }
    }

    fun drawText(text: String, canvas: Canvas){
        canvas.drawText(text, width / 2f, height / 2f, paint)
    }
}
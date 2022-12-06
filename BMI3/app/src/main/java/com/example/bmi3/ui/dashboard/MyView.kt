package com.example.bmi3.ui.dashboard

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class MyView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var lista = listOf<String?>()
    var x1 = 50f
    var y1 = 100f
    var pituus = lista.size

    // Tämä ei ole dynaaminen, getwidht tjsp puuttuu
    var rectangleWidth = 413f / pituus
    var rectangleFloat = rectangleWidth.toFloat()
    var padding = 5.0f
    var colors = intArrayOf(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN, Color.BLACK, Color.MAGENTA, Color.GRAY)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val paint = Paint()

        for (item in lista) {
            paint.style = Paint.Style.FILL
            var randomColor = colors.random()
            paint.color = randomColor
            try{
                var height : Float = item!!.toFloat()
                println(height)


                // TOIMII MUTTA VÄÄRINPÄIN
                canvas?.drawRect(x1, 30f, x1 + 50f, height*40, paint)


                println("Padding: " + padding + " x1: " + x1 + " height:" + height)

            } catch (e: NumberFormatException){ // handle your exception
                println(e)
            }

            // canvas?.drawRect(x1, height.toFloat(), 80f, 50f, paint)

            x1 += 55
            // height = listan arvo
        }
    }

    fun setList(myList: List<String?>?){
        if (myList != null) {
            lista = myList
        }
    }

}
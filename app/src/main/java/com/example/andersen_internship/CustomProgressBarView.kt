package com.example.andersen_internship

import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class CustomProgressBarView : View {
    private var valueProgressAnimation = getScreenWidth().toFloat()
    private val screenWidth = getScreenWidth().toFloat()
    private var valueAnimator = ValueAnimator()
    private var mRect: RectF? = null
    private var mPaint: Paint? = null

    constructor(context: Context?) : super(context) { init() }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) { init() }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) { init() }

    private fun init() {

        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint!!.color = Color.RED
        mPaint!!.style = Paint.Style.STROKE
        mPaint!!.strokeWidth = 20f
        mPaint!!.strokeCap = Paint.Cap.BUTT
        mRect = RectF(0F, 10F, getScreenWidth().toFloat(), 10F)

        valueAnimator = ValueAnimator.ofFloat(0F, valueProgressAnimation)
        valueAnimator.duration = 5000
        valueAnimator.addUpdateListener(AnimatorUpdateListener { animation ->
            valueProgressAnimation = animation.animatedValue as Float
            invalidate()
        })

        valueAnimator.setFloatValues(0F, valueProgressAnimation)
        valueAnimator.start()
    }

    override fun onMeasure(
        widthMeasureSpec: Int,
        heightMeasureSpec: Int
    ) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec)
        setMeasuredDimension(getScreenWidth(), measuredHeight)
    }

    override fun onDraw(c: Canvas) {
        if (valueProgressAnimation < screenWidth * 0.15)
            mPaint!!.color = Color.RED
        else if (valueProgressAnimation > screenWidth * 0.15 && valueProgressAnimation < screenWidth * 0.6)
            mPaint!!.color = Color.YELLOW
        else if (valueProgressAnimation > screenWidth * 0.6)
            mPaint!!.color = Color.GREEN
        mRect = RectF(0F, 10F, valueProgressAnimation, 10F)
        c.drawRect(mRect!!, mPaint!!)
    }

    fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }
}
package asiantech.internship.summer.canvas

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-nhatnguyen`.activity_paint.*

class PaintActivity : AppCompatActivity() {

    private lateinit var appPaint: MyAppPaint
    private var isClick: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paint)

        tvDraw.setBackgroundResource(R.color.colorBackground)
        appPaint = findViewById(R.id.drawing)
        btnErase.setOnClickListener {
            appPaint.setErase(true)
            isClick = true
            background()
            Log.d("xxxxx", "$isClick")
        }

        btnDraw.setOnClickListener {
            appPaint.setErase(false)
            isClick = false
            background()
            Log.d("xxxxx", "$isClick")
        }
    }

    private fun background() {
        if (isClick == false) {
            tvDraw.setBackgroundResource(R.color.colorBackground)
            tvErase.setBackgroundResource(R.color.colorWhite)
        } else {
            tvErase.setBackgroundResource(R.color.colorBackground)
            tvDraw.setBackgroundResource(R.color.colorWhite)
        }
    }
}

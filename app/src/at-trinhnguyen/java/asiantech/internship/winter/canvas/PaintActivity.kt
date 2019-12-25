package asiantech.internship.winter.canvas

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-trinhnguyen`.activity_paint.*

class PaintActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paint)

        tbEraser.setOnCheckedChangeListener { _, isChecked ->
            paintView.setEraser(isChecked)
        }

        tbPickColor.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                colorSeekBar.visibility = View.VISIBLE
            } else {
                colorSeekBar.visibility = View.INVISIBLE
            }
        }


        colorSeekBar.setOnColorChangeListener(object : ColorSeekBar.OnColorChangeListener {
            override fun onColorChangeListener(color: Int) {
                paintView.setPaintColor(color)
            }
        })
    }
}

package asiantech.internship.winter.canvas

import android.os.Bundle
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
    }
}

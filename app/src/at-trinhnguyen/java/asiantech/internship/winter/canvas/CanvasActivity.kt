package asiantech.internship.winter.canvas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-trinhnguyen`.activity_canvas.*

class CanvasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canvas)

        tvDraw.setOnClickListener {
            startActivity(Intent(this, PaintActivity::class.java))
        }
    }
}

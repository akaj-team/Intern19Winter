package asiantech.internship.summer.customview_canvas

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-myhuynh`.activity_paint.*
import yuku.ambilwarna.AmbilWarnaDialog

class PaintActivity : AppCompatActivity(), View.OnClickListener {

    private var isPaint = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paint)

        imgEraser.setOnClickListener(this)
        imgDraw.setOnClickListener(this)
        imgColor.setOnClickListener(this)
    }

    private fun setIsPaint() {
        if (isPaint) {
            imgDraw.setImageResource(R.drawable.ic_mode_edit_red_48dp)
            imgEraser.setImageResource(R.drawable.ic_eraser_48dp)
        } else {
            imgDraw.setImageResource(R.drawable.ic_mode_edit_gray_48dp)
            imgEraser.setImageResource(R.drawable.ic_eraser_red_48dp)
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            imgDraw -> {
                isPaint = true
                draw.setEraser(false)
            }

            imgEraser -> {
                isPaint = false
                draw.setEraser(true)
            }

            imgColor -> {
                showColorPickerDialog()
            }
        }
        setIsPaint()
    }

    private fun showColorPickerDialog() {
        AmbilWarnaDialog(this, Color.BLACK, false, object : AmbilWarnaDialog.OnAmbilWarnaListener {
            override fun onCancel(dialog: AmbilWarnaDialog?) {}

            override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                imgColor.setColorFilter(color, PorterDuff.Mode.SRC_IN)
                draw.setPaintColor(color)
            }
        }).show()
    }
}

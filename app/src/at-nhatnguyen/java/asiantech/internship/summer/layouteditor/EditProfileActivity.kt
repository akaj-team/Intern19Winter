package asiantech.internship.summer.layouteditor

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-nhatnguyen`.activity_edit_profile.*

class EditProfileActivity : AppCompatActivity() {
    companion object{
        const val REQUEST_IMAGE_CAPTURE = 100
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        tvEditPicture.setOnClickListener {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(packageManager)?.also {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imgBitmap = data?.extras?.get("data") as Bitmap
            circleImageView.setImageBitmap(imgBitmap)
        }
    }
}

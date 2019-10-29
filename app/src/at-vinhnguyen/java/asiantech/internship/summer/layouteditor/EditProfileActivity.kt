package asiantech.internship.summer.layouteditor

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.R
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.`at-vinhnguyen`.activity_edit_profile.*

class EditProfileActivity : AppCompatActivity(), View.OnClickListener {

  val REQUEST_IMAGE_CAPTURE = 1

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_edit_profile)
    val civProfileImage = findViewById<CircleImageView>(R.id.civProfile)
    civProfileImage.setOnClickListener(this)
  }

  private fun dispatchTakePictureIntent() {
    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
      takePictureIntent.resolveActivity(packageManager)?.also {
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
      }
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
      val imageBitmap = data?.extras?.get("data") as Bitmap
      civProfile.setImageBitmap(imageBitmap)
    }
  }

  override fun onClick(p0: View?) {
    if (p0 != null) {
      when (p0.id) {
        R.id.civProfile -> dispatchTakePictureIntent()
      }
    }
  }
}

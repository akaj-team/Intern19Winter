package asiantech.internship.summer.savedatabase.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.summer.savedatabase.database.DBHandling
import asiantech.internship.summer.savedatabase.database.UserModel
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_register.*

class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
        const val REQUEST_IMAGE_CAPTURE = 1
        const val REQUEST_IMAGE_GALLERY = 2
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        imgAvatar.setOnClickListener {
            showPictureDialog()
        }

        btnCreateAccount.setOnClickListener {
            val userHandling = context?.let { it1 -> DBHandling(it1) }
            val fullName = edtFullName.text.toString()
            val email = edtEmail.text.toString()
            val pass = edtPassword.text.toString()

            val a = userHandling?.insertUser(UserModel(fullName = fullName, email = email, password = pass))
            Toast.makeText(activity, "$a", Toast.LENGTH_SHORT).show()
            fragmentManager?.beginTransaction()?.
                    replace(R.id.frameLayout,LoginFragment.newInstance(email,pass))?.
                    commit()
        }

        tvLoginHere.setOnClickListener {
            val email = ""
            val pass = ""
            fragmentManager?.beginTransaction()?.replace(R.id.frameLayout, LoginFragment.newInstance(email,pass))?.addToBackStack(null)?.commit()
        }

    }

    private fun takePhotoFromCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            activity?.packageManager?.let {
                takePictureIntent.resolveActivity(it)?.also {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    private fun choosePhotoFromGallery() {
        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).also { takePictureIntent ->
            activity?.packageManager?.let {
                takePictureIntent.resolveActivity(it)?.also {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_GALLERY)
                }
            }
        }
    }

    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(activity)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItem = arrayOf("Open your camera", "Take from gallery")
        pictureDialog.setItems(pictureDialogItem) { _, which ->
            when (which) {
                0 -> takePhotoFromCamera()
                1 -> choosePhotoFromGallery()
            }
        }
        pictureDialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == Activity.RESULT_OK) {
            imgAvatar.setImageURI(data?.data)
        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imgBitMap = data?.extras?.get("data") as Bitmap
            imgAvatar.setImageBitmap(imgBitMap)
        }
    }
}

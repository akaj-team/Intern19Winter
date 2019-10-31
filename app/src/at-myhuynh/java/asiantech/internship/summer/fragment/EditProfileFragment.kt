package asiantech.internship.summer.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-myhuynh`.fragment_edit_profile.*

class EditProfileFragment : Fragment() {

    private var mFullName: String? = null
    private var mEmail: String? = null

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
        private const val ARG_USER_NAME = "user"
        private const val ARG_EMAIL = "email"
        fun newInstance(fullName: String? = "", email: String = "") =
                EditProfileFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_USER_NAME, fullName)
                        putString(ARG_EMAIL, email)
                    }

                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mFullName = it.getString(ARG_USER_NAME)
            mEmail = it.getString(ARG_EMAIL)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edtFullName.setText(mFullName)
        edtEmail.setText(mEmail)

        tvEditPicture.setOnClickListener {
            dispatchTakePictureIntent()
        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            activity?.packageManager?.let {
                takePictureIntent.resolveActivity(it)?.also {
                    activity?.startActivityFromFragment(this, takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imgAvatar.setImageBitmap(imageBitmap)
        }
    }
}

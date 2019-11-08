package asiantech.internship.summer.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_edit_profile.*
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_edit_profile.edtEmail
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_edit_profile.edtFullName
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_edit_profile.tvEditPicture

class EditProfileFragment : Fragment() {
    companion object {
        private const val ARG_PARA1 = "email"
        private const val ARG_PARA2 = "pass"
        private const val ARG_PARA3 = "fullName"
        const val REQUEST_IMAGE_CAPTURE = 100
        fun newInstance(email: String, pass: String, fullName: String) = EditProfileFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARA1, email)
                putString(ARG_PARA2, pass)
                putString(ARG_PARA3, fullName)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments.let {
            edtEmail.setText(it?.getString(ARG_PARA1))
            edtFullName.setText(it?.getString((ARG_PARA3)))
        }

        tvEditPicture.setOnClickListener {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                activity?.packageManager?.let { it1 ->
                    takePictureIntent.resolveActivity(it1)?.also {
                        activity?.startActivityFromFragment(this, takePictureIntent, REQUEST_IMAGE_CAPTURE)
                    }
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

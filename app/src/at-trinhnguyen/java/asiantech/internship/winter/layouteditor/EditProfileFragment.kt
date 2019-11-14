package asiantech.internship.winter.layouteditor


import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.`at-trinhnguyen`.fragment_edit_profile.*

class EditProfileFragment : Fragment() {

    companion object {
        private val instance = EditProfileFragment()
        @JvmStatic
        fun newInstance() = instance
        const val ARG_EMAIL = "arg_email"
        const val ARG_PHONE = "arg_phone"
        const val REQUEST_IMAGE_CAPTURE = 1
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(asiantech.internship.summer.R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //send data to activity using view model
        activity?.let {
            val viewModel = ViewModelProviders.of(it).get(SharedViewModel::class.java)
            btnSaveProfile.setOnClickListener {
                viewModel.bundle.postValue(Bundle().apply {
                    putString(ARG_EMAIL, edtEmail.text.toString())
                    putString(ARG_PHONE, edtPhone.text.toString())
                })
            }
        }

        //take photo from camera
        tvEditProfilePicture.setOnClickListener {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                activity?.apply {
                    takePictureIntent.resolveActivity(packageManager)?.also {
                        startActivityFromFragment(instance, takePictureIntent, REQUEST_IMAGE_CAPTURE)
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imgProfile.setImageBitmap(imageBitmap)
        }
    }
}

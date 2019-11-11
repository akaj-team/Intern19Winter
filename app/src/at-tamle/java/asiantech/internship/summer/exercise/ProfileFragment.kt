package asiantech.internship.summer.exercise

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
import kotlinx.android.synthetic.`at-tamle`.fragment_profile.*
import kotlinx.android.synthetic.`at-tamle`.fragment_profile.edtEmail

class ProfileFragment : Fragment() {
    private var mName: String? = null
    private var mEmail: String? = null
companion object {
    const val REQUEST_IMAGE_CAPTURE = 1
    const val ARG_NAME = "name"
    const val ARG_EMAIL = "email"
    fun newInstance(user: String? = "", email: String = ""): ProfileFragment {

        return ProfileFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_NAME, user)
                putString(ARG_EMAIL, email)
            }
        }
    }
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mName = it.getString(ARG_NAME)
            mEmail = it.getString(ARG_EMAIL)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments.let {

            edtEmail.setText(it?.getString(ARG_EMAIL))
            edtFullName.setText(it?.getString((ARG_NAME)))
        }

        tvPicture.setOnClickListener {
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
            imgProfile.setImageBitmap(imageBitmap)
        }
    }
}
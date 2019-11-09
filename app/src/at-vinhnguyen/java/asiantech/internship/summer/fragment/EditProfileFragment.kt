package asiantech.internship.summer.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-vinhnguyen`.fragment_edit_profile.*

class EditProfileFragment : Fragment(), View.OnClickListener {

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 1
        const val ARG_USER = "user"
        const val ARG_EMAIL = "email"
        fun newInstance(user: String = "", email: String = ""): EditProfileFragment {

            return EditProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USER, user)
                    putString(ARG_EMAIL, email)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        //Receive data from LoginFragment and fill in editor
        edtFullName.setText(arguments?.getString(ARG_USER).toString())
        edtEmail.setText(arguments?.getString(ARG_EMAIL).toString())
        civProfile.setOnClickListener(this)
        btnSaveProfile.setOnClickListener(this)
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            activity?.packageManager?.let {
                takePictureIntent.resolveActivity(it).also {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
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
                R.id.btnSaveProfile -> {
                    val mainActivity: MainActivity = activity as MainActivity
                    mainActivity.replaceFragment(RegisterFragment.newInstance(edtFullName.text.toString(),
                            edtEmail.text.toString()))
                }
            }
        }
    }
}

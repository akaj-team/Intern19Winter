package asiantech.internship.winter.savedata.ui.editprofile

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import asiantech.internship.summer.R
import asiantech.internship.summer.databinding.FragmentEditProfileBinding
import asiantech.internship.winter.savedata.db.TodoDatabase
import kotlinx.android.synthetic.`at-trinhnguyen`.fragment_edit_profile.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class EditProfileFragment : Fragment() {

    private var currentPhotoPath: Uri? = null

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 111
        private const val REQUEST_GET_CONTENT_IMAGE = 222
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil
                .inflate<FragmentEditProfileBinding>(inflater, R.layout.fragment_edit_profile, container, false)
        val application = requireNotNull(activity?.application)
        val dataSource = TodoDatabase.getInstance(application)
        var idUser = 0L
        activity?.getPreferences(Context.MODE_PRIVATE)?.apply {
            idUser = getLong(getString(R.string.pref_id_user), 0L)
        }
        val viewModelFactory = EditProfileViewModelFactory(idUser, dataSource, application)
        val editProfileViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(EditProfileViewModel::class.java)
        binding.editProfileViewModel = editProfileViewModel

        editProfileViewModel.getUser().observe(viewLifecycleOwner, Observer {
            binding.imgProfile.setImageURI(Uri.parse(it.avatarPath))
        })


        binding.btnSaveProfile.setOnClickListener {
            editProfileViewModel.getUser().observe(this, Observer {
                it.copy(username = binding.edtFullName.text.toString(), avatarPath = currentPhotoPath.toString())
                        .let { user ->
                            editProfileViewModel.update(user)
                        }
            })
            findNavController().navigate(R.id.action_editProfileFragment_to_homeFragment)

        }

        binding.tvEditProfilePicture.setOnClickListener {
            context?.let { it1 ->
                AlertDialog.Builder(it1).apply {
                    setTitle("Choose Action ")
                    setMessage("Take photo from camera or gallery?")

                    setPositiveButton("Camera") { _, _ ->
                        dispatchTakePictureIntent()
                    }

                    setNegativeButton("Gallery") { _, _ ->
                        val intent = Intent(Intent.ACTION_GET_CONTENT)
                        intent.type = "image/*"
                        intent.resolveActivity(context.packageManager)?.also {
                            startActivityForResult(intent, REQUEST_GET_CONTENT_IMAGE)
                        }
                    }

                    setNeutralButton("Cancel") { _, _ ->
                    }
                }.create().show()
            }
        }

        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_IMAGE_CAPTURE -> if (resultCode == RESULT_OK) {
                imgProfile.setImageURI(currentPhotoPath)
            }
            REQUEST_GET_CONTENT_IMAGE -> if (resultCode == RESULT_OK) {
                currentPhotoPath = data?.data
                imgProfile.setImageURI(currentPhotoPath)
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(Date())
        val storageDir: File? = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
                "JPEG_${timeStamp}_", /* prefix */
                ".jpg", /* suffix */
                storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = Uri.fromFile(File(absolutePath))
        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            context?.packageManager?.let {
                takePictureIntent.resolveActivity(it)?.also {
                    // Create the File where the photo should go
                    val photoFile: File? = try {
                        createImageFile()
                    } catch (ex: IOException) {
                        // Error occurred while creating the File
                        null
                    }
                    // Continue only if the File was successfully created
                    photoFile?.also {
                        val photoURI: Uri? = context?.let { context ->
                            FileProvider.getUriForFile(
                                    context,
                                    "asiantech.internship.winter",
                                    it
                            )
                        }
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                    }
                }
            }
        }
    }
}

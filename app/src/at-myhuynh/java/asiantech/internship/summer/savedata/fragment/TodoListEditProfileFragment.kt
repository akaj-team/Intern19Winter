package asiantech.internship.summer.savedata.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.summer.savedata.Constants.Companion.OPTION_CANCEL
import asiantech.internship.summer.savedata.Constants.Companion.OPTION_CHOOSE_GALLERY
import asiantech.internship.summer.savedata.Constants.Companion.OPTION_TAKE_PHOTO
import asiantech.internship.summer.savedata.Constants.Companion.REQUEST_IMAGE_CAPTURE
import asiantech.internship.summer.savedata.TodoListActivity
import asiantech.internship.summer.savedata.data.TodoListDatabaseHelper
import asiantech.internship.summer.savedata.entity.User
import kotlinx.android.synthetic.`at-myhuynh`.fragment_todo_list_edit_profile.*
import java.io.ByteArrayOutputStream

class TodoListEditProfileFragment : Fragment() {

    private lateinit var userDatabaseHelper: TodoListDatabaseHelper
    private lateinit var user: User
    private var userId = -1
    private var pathImage: String? = null

    companion object {
        private const val ARG_USER_ID = "userId"
        fun newInstance(userId: Int) = TodoListEditProfileFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_USER_ID, userId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userId = it.getInt(ARG_USER_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo_list_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        userDatabaseHelper = TodoListDatabaseHelper(requireContext())
        user = userDatabaseHelper.readUser(userId)
        edtFullName.setText(user.userName)
        edtNickName.setText(user.nickname)

        if (user.avatar != null) {
            imgAvatar.setImageBitmap(BitmapFactory.decodeFile("${user.avatar}"))
        } else {
            imgAvatar.setImageResource(R.drawable.ic_avatar_default)
        }
        imgAvatar.setOnClickListener {
            dispatchTakePictureIntent(requireContext())
        }

        btnSave.setOnClickListener {
            user.userName = edtFullName.text.toString()
            user.nickname = edtNickName.text.toString()
            user.avatar = pathImage

            if (userDatabaseHelper.updateUser(user) != 0) {
                Toast.makeText(requireContext(), "Update Ok", Toast.LENGTH_SHORT).show()
                (activity as? TodoListActivity)?.replaceFragment(TodoListHomeFragment.newInstance(userId), false)
            } else {
                Toast.makeText(requireContext(), "Update Fail", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun dispatchTakePictureIntent(context: Context) {
        val options = arrayOf(OPTION_TAKE_PHOTO, OPTION_CHOOSE_GALLERY, OPTION_CANCEL)

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Choose your profile picture")

        builder.setItems(options) { dialog, item ->
            when (options[item]) {
                OPTION_TAKE_PHOTO -> {
                    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                        takePictureIntent.resolveActivity(context.packageManager)?.also {
                            startActivityForResult(takePictureIntent, 111)
                        }
                    }
                }

                OPTION_CHOOSE_GALLERY -> {
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).also { takePictureIntent ->
                        takePictureIntent.resolveActivity(context.packageManager)?.also {
                            startActivityForResult(takePictureIntent, 111)
                        }
                    }
                }
                OPTION_CANCEL -> dialog.dismiss()
            }
        }
        builder.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    imgAvatar.setImageBitmap(imageBitmap)
                    val projection = arrayOf(MediaStore.Images.Media.DATA)
                    val imageUri = getImageUri(imageBitmap)
                    val cursor = imageUri?.let { requireContext().contentResolver.query(it, projection, null, null, null) }
                    val index = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                    cursor?.moveToFirst()
                    pathImage = index?.let { cursor.getString(it) }.toString()
                }
                else -> {
                    imgAvatar.setImageURI(data?.data)
                    val projection = arrayOf(MediaStore.Images.Media.DATA)
                    val imageUri = data?.data
                    val cursor = imageUri?.let { requireContext().contentResolver.query(it, projection, null, null, null) }
                    val index = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                    cursor?.moveToFirst()
                    pathImage = index?.let { cursor.getString(it) }.toString()
                }
            }
        }
    }

    private fun getImageUri(inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(requireContext().contentResolver, inImage, "Title", null)
        return Uri.parse(path)
    }
}

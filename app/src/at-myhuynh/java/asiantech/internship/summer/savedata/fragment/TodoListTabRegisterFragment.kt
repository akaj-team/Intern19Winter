package asiantech.internship.summer.savedata.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
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
import asiantech.internship.summer.savedata.data.TodoListDatabaseHelper
import asiantech.internship.summer.savedata.entity.User
import kotlinx.android.synthetic.`at-myhuynh`.fragment_todo_list_tab_register.*
import java.io.ByteArrayOutputStream

class TodoListTabRegisterFragment : Fragment() {

    private lateinit var userDatabase: TodoListDatabaseHelper
    private var pathImage: String? = null

    companion object{
        fun newInstance() = TodoListTabRegisterFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo_list_tab_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        userDatabase = TodoListDatabaseHelper(requireContext())

        imgAvatar.setOnClickListener {
            dispatchTakePictureIntent(requireContext())
        }

        btnSignUp.setOnClickListener {
            register()
        }

        tvLoginHere.setOnClickListener {
            val users = userDatabase.readUsers()
            users.forEach {
                Log.d("xyz", "${it.id} - ${it.userName} - ${it.password}")
            }
        }
    }

    private fun register() {
        val user = User(0, edtFullName.text.toString(), edtPassword.text.toString(), edtNickName.text.toString(), pathImage)
        userDatabase.insertUser(user)
        Toast.makeText(requireContext(), "Inserted", Toast.LENGTH_SHORT).show()
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
                            (context as Activity).startActivityForResult(takePictureIntent, 0)
                        }
                    }
                }

                OPTION_CHOOSE_GALLERY -> {
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).also { takePictureIntent ->
                        takePictureIntent.resolveActivity(context.packageManager)?.also {
                            (context as Activity).startActivityForResult(takePictureIntent, 1)
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
        Log.d("zzz", "On ActivityResult")
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
                    val uri = data?.data
                    val cursor = uri?.let { requireContext().contentResolver.query(it, projection, null, null, null) }
                    val index = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                    cursor?.moveToFirst()
                    pathImage = index?.let { cursor.getString(it) }.toString()
                }
            }
        }
    }

    private fun getImageUri(bitmap: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(requireContext().contentResolver, bitmap, "Title", null)
        return Uri.parse(path)
    }
}

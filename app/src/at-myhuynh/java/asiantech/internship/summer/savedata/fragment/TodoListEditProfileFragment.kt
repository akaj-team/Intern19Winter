package asiantech.internship.summer.savedata.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.summer.savedata.Constants.Companion.OPTION_CANCEL
import asiantech.internship.summer.savedata.Constants.Companion.OPTION_CHOOSE_GALLERY
import asiantech.internship.summer.savedata.Constants.Companion.OPTION_TAKE_PHOTO
import kotlinx.android.synthetic.`at-myhuynh`.fragment_todo_list_edit_profile.*

class TodoListEditProfileFragment : Fragment() {

    companion object {
        fun newInstance() = TodoListEditProfileFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo_list_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        imgAvatar.setOnClickListener {
            dispatchTakePictureIntent(requireContext())
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
}

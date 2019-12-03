package asiantech.internship.summer.savedata

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.graphics.drawable.Drawable
import asiantech.internship.summer.R
import com.google.android.material.snackbar.Snackbar
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.`at-tamle`.fragment_register.imgAvatar
import kotlinx.android.synthetic.`at-tamle`.fragment_register.*
import kotlinx.android.synthetic.`at-tamle`.fragment_register.edtNickname
import kotlinx.android.synthetic.`at-tamle`.fragment_register.edtPassword
import kotlinx.android.synthetic.`at-tamle`.fragment_register.edtUserName

class RegisterFragment : Fragment(), View.OnClickListener {
    private var mUser: User? = null
    private val mOldDrawable: Drawable? = null
    private var mPath: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgAvatar.setOnClickListener(this)
        btnRegister.setOnClickListener(this)
        mUser = activity?.let { User(it) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val result = CropImage.getActivityResult(data)
            imgAvatar.setImageURI(result.uri)
        } else if (resultCode == RESULT_CANCELED) {
            Snackbar.make(btnRegister, R.string.ban_chua_chon_anh_dai_dien, Snackbar.LENGTH_SHORT).setAction(R.string.action_ok, {}).show()
        } else {
            Snackbar.make(btnRegister, R.string.co_loi_xay_ra, Snackbar.LENGTH_SHORT).setAction(R.string.action_ok, {}).show()
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.imgAvatar -> takeImageFromCameraOrGallery()
            R.id.btnRegister -> doRegister()
        }
    }

    private fun takeImageFromCameraOrGallery() {
        CropImage.activity().start(activity as LoginRegisterActivity)
    }

    private fun doRegister() {
        val username = edtUserName.getText().toString().trim()
        val nickname = edtNickname.getText().toString().trim()
        val password = edtPassword.getText().toString().trim()

        if (imgAvatar.getDrawable() == mOldDrawable) {
            Snackbar.make(btnRegister, R.string.ban_chua_chon_anh_dai_dien, Snackbar.LENGTH_SHORT).show()
        } else if (username.isEmpty()) {
            Snackbar.make(btnRegister, R.string.username_ko_hop_le, Snackbar.LENGTH_SHORT).show()
        } else if (mUser!!.isInvalid(username)) {
            Snackbar.make(btnRegister, R.string.username_da_dc_dang_ky, Snackbar.LENGTH_SHORT).show()
        } else if (nickname.isEmpty()) {
            Snackbar.make(btnRegister, R.string.nickname_ko_hop_le, Snackbar.LENGTH_SHORT).show()
        } else if (password.isEmpty()) {
            Snackbar.make(btnRegister, R.string.mat_khau_ko_hop_le, Snackbar.LENGTH_SHORT).show()
        } else {
            val user = DataUser()
            user.photoPath = mPath
            user.userName = username
            user.nickname = nickname
            user.password = password
            val insert = mUser?.insert(user)
            if (insert == true) {
                imgAvatar.setImageResource(R.drawable.img_chibi)
                edtUserName.setText("")
                edtNickname.setText("")
                edtPassword.setText("")

                Snackbar.make(btnRegister, R.string.dang_ky_thanh_cong, Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(btnRegister, R.string.dang_ky_that_bai, Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}

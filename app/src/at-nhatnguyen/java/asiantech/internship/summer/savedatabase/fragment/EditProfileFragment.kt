package asiantech.internship.summer.savedatabase.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.summer.savedatabase.database.DBHandling
import asiantech.internship.summer.savedatabase.database.UserModel
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_edit_profile.*

class EditProfileFragment : Fragment() {


    companion object {
        private const val ARG_USER_ID = "userId"
        fun newInstance(userId: Int) = EditProfileFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_USER_ID, userId)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dbHandling = DBHandling(requireContext())
        val userId = arguments?.getInt(ARG_USER_ID)
        Log.d("xxx","id = $userId")
        val users = dbHandling.readUser(userId!!)
        edtFullName.setText(users.fullName)
        edtEmail.setText(users.email)
        edtPassword.setText(users.password)

        btnSaveProfile.setOnClickListener {
            val email = edtEmail.text.toString()
            val fullName = edtFullName.text.toString()
            val pass = edtPassword.text.toString()
            val userModel = UserModel(userId,fullName,email,pass)
            dbHandling.updateUser(userModel)
            val a = users.fullName
            Log.d("xxx","name = $a")
            fragmentManager?.beginTransaction()?.
                    replace(R.id.frameLayoutActivity,ViewPagerFragment.newInstance(userId))?.
                    commit()
        }
    }
}

package asiantech.internship.winter.layouteditor


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.`at-trinhnguyen`.fragment_edit_profile.*

class EditProfileFragment : Fragment() {

    companion object {
        val instance = EditProfileFragment()
        const val ARG_EMAIL = "arg_email"
        const val ARG_PHONE = "arg_phone"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
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
    }
}

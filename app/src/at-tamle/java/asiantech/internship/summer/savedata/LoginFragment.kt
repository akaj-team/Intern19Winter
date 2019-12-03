package asiantech.internship.summer.savedata

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.`at-tamle`.fragment_login.*
import kotlinx.android.synthetic.`at-tamle`.fragment_tablayout.*

class LoginFragment : Fragment(), View.OnClickListener {
    private var mPreference: Preference? = null
    private var mUser: User? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogin.setOnClickListener(this)

        mPreference = Preference(activity as LoginRegisterActivity)
        mUser = User(activity as LoginRegisterActivity)
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.btnLogin) {
            val userName = edtUserName.getText().toString().trim()
            val password = edtPassword.getText().toString().trim()
            if (userName.isEmpty()) {
                Snackbar.make(btnLogin, getString(R.string.tai_khoan_ko_hop_le), Snackbar.LENGTH_SHORT).setAction(getString(R.string.action_ok), {}).show()
            } else if (password.isEmpty()) {
                Snackbar.make(btnLogin, getString(R.string.mat_khau_ko_hop_le), Snackbar.LENGTH_SHORT).setAction(getString(R.string.action_ok), {}).show()
            } else {
                val user = mUser?.getUser(userName, password)
                if (user != null) {
                    mPreference?.putInt(Preference.ID_USER, user.id)
                    startActivity(Intent(context, SavedataActivity::class.java))
                } else {
                    Snackbar.make(btnLogin, getString(R.string.mat_khau_ko_dung), Snackbar.LENGTH_SHORT).setAction(getString(R.string.action_ok), {}).show()
                }
            }
        }
    }
}

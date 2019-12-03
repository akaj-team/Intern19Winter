package asiantech.internship.summer.savedata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.`at-tamle`.fragment_add.*

class AddTodoFragment : Fragment(), View.OnClickListener {
    private var mTodo: Todo? = null
    private var mDataUser: DataUser? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as SavedataActivity).supportActionBar?.setTitle(R.string.menu_add_todo)

        val idUser = Preference(activity as SavedataActivity).getInt(Preference.ID_USER)
        if (idUser != 0) {
            mDataUser = User(activity as SavedataActivity).getUser(idUser)
            mTodo = Todo(activity as SavedataActivity)
        }

        btnAddTodo.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.btnAddTodo) {
            val sTodo = edtNewTodo.getText().toString().trim()
            if (sTodo.isEmpty()) {
                Snackbar.make(btnAddTodo, R.string.ko_dc_de_trong, Snackbar.LENGTH_SHORT).setAction(R.string.action_ok, {}).show()
                return
            }
            val result: Boolean = mTodo?.insert(DataTodo(1234567890, mDataUser!!.id, sTodo, 0))!!
            Snackbar.make(btnAddTodo, if (result) R.string.them_thanh_cong else R.string.them_that_bai, Snackbar.LENGTH_SHORT).setAction(R.string.action_ok, {}).show()
        }
    }
}

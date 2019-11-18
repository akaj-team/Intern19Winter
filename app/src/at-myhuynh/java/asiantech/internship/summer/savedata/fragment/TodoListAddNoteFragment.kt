package asiantech.internship.summer.savedata.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.summer.savedata.TodoListActivity
import kotlinx.android.synthetic.`at-myhuynh`.fragment_todo_list_add_note.*

class TodoListAddNoteFragment : Fragment() {

    private var mTitle: String? = null

    companion object {
        private const val ARG_TODO_TITLE = "title"
        fun newInstance(title: String = "") = TodoListAddNoteFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_TODO_TITLE, title)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mTitle = it.getString(ARG_TODO_TITLE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo_list_add_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        edtAddNote.setText(mTitle)
        btnAddNote.setOnClickListener {
            (activity as? TodoListActivity)?.replaceFragment(TodoListHomeFragment.newInstance(), false)
        }
    }
}

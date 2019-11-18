package asiantech.internship.summer.savedata.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.R
import asiantech.internship.summer.savedata.entity.Todo
import asiantech.internship.summer.savedata.adapter.TodoListHomeDoneAdapter
import kotlinx.android.synthetic.`at-myhuynh`.fragment_todo_list_home_done.*

class TodoListHomeDoneFragment : Fragment() {

    companion object {
        private var mTodoLists = mutableListOf<Todo>()
        fun newInstance(todoLists: MutableList<Todo>) = TodoListHomeDoneFragment().apply {
            mTodoLists = todoLists
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo_list_home_done, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvHomeDone.layoutManager = LinearLayoutManager(requireContext())
        rvHomeDone.adapter = TodoListHomeDoneAdapter(mTodoLists)
    }
}

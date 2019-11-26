package asiantech.internship.summer.savedata.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.R
import asiantech.internship.summer.savedata.adapter.TodoListHomeDoneAdapter
import asiantech.internship.summer.savedata.data.TodoListDatabaseHelper
import asiantech.internship.summer.savedata.entity.Todo
import kotlinx.android.synthetic.`at-myhuynh`.fragment_todo_list_home_done.*

class TodoListHomeDoneFragment : Fragment() {

    private lateinit var todoDataBase: TodoListDatabaseHelper
    private lateinit var todoListDones: MutableList<Todo>
    private lateinit var todoListDoneAdapter: TodoListHomeDoneAdapter
    private var userId = -1

    companion object {
        private const val ARG_USER_ID = "userId"
        fun newInstance(userId: Int) = TodoListHomeDoneFragment().apply {
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
        todoDataBase = TodoListDatabaseHelper(requireContext())
        todoListDones = todoDataBase.readTodoDones(userId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo_list_home_done, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvHomeDone.layoutManager = LinearLayoutManager(requireContext())
        todoListDoneAdapter = TodoListHomeDoneAdapter(todoListDones)
        rvHomeDone.adapter = todoListDoneAdapter
    }
}

package asiantech.internship.summer.retrofit.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.R
import asiantech.internship.summer.retrofit.adapter.TodoListHomeDoneAdapter
import asiantech.internship.summer.retrofit.model.Todo
import asiantech.internship.summer.retrofit.viewmodel.TodoViewModel
import kotlinx.android.synthetic.`at-myhuynh`.fragment_todo_list_home_done.*

class TodoListHomeDoneFragment : Fragment() {

    private lateinit var todoListDones: MutableList<Todo>
    private lateinit var todoListDoneAdapter: TodoListHomeDoneAdapter
    private lateinit var todoViewModel: TodoViewModel

    companion object {
        fun newInstance() = TodoListHomeDoneFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo_list_home_done, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        todoListDones = mutableListOf()
        rvHomeDone.layoutManager = LinearLayoutManager(requireContext())
        todoListDoneAdapter = TodoListHomeDoneAdapter(todoListDones)
        rvHomeDone.adapter = todoListDoneAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        todoViewModel = ViewModelProviders.of(requireActivity()).get(TodoViewModel::class.java)
        getAllTodo()
    }

    private fun getAllTodo() {
        todoViewModel.getTodoResponseLiveData().observe(this, Observer<MutableList<Todo>> { t ->
            t?.let {
                todoListDones.addAll(t)
            }
            todoListDoneAdapter.notifyDataSetChanged()
        })
    }
}

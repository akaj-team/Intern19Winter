package asiantech.internship.winter.savedata.ui.todo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import asiantech.internship.summer.R
import asiantech.internship.summer.databinding.FragmentTodoBinding
import asiantech.internship.winter.savedata.db.todo.Todo
import asiantech.internship.winter.savedata.db.todo.TodoDatabase

class TodoFragment : Fragment() {
    private lateinit var binding: FragmentTodoBinding
    private lateinit var todoViewModel: TodoViewModel
    private val idUser = 111L

    companion object {
        fun newInstance() = TodoFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("aaa", "aaaaa")
        arguments?.let {
            Log.d("aaa", "bbbbb")
            todoViewModel.insert(Todo(0, idUser, it.getString("title")
                    ?: "null", it.getString("description") ?: "null", false))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todo, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(activity?.application)
        val dataSource = TodoDatabase.getInstance(application).todoDao
        val viewModelFactory = TodoViewModelFactory(dataSource, application)
        todoViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(TodoViewModel::class.java)
        binding.todoViewModel = todoViewModel
        val adapter = TodoAdapter(TodoListener { idTodo, viewId ->
            when (viewId) {
                R.id.btnDelete -> Toast.makeText(context, "$idTodo delete", Toast.LENGTH_LONG).show()
                R.id.btnEdit -> Toast.makeText(context, "$idTodo edit ", Toast.LENGTH_LONG).show()
                R.id.btnDone -> Toast.makeText(context, "$idTodo done", Toast.LENGTH_LONG).show()
            }
        })

        todoViewModel.todos.observe(viewLifecycleOwner, Observer {
            it?.let { adapter.setTodos(it) }
        })
        binding.recyclerView.adapter = adapter
        binding.fab.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_newTodoFragment)
        }
        binding.lifecycleOwner = this
    }
}

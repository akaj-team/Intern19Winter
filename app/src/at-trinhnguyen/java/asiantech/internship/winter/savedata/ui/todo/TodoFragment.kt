package asiantech.internship.winter.savedata.ui.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import asiantech.internship.summer.R
import asiantech.internship.summer.databinding.FragmentTodoBinding
import asiantech.internship.winter.savedata.db.todo.Todo
import asiantech.internship.winter.savedata.db.todo.TodoDatabase
import kotlinx.android.synthetic.`at-trinhnguyen`.fragment_todo.*

class TodoFragment : Fragment() {
    private lateinit var binding: FragmentTodoBinding
    companion object {
        fun newInstance() = TodoFragment()
    }

    private lateinit var viewModel: TodoViewModel

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
        val todoViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(TodoViewModel::class.java)
        binding.todoViewModel = todoViewModel
        val adapter = context?.let { TodoAdapter(it) }

        todoViewModel.todos.observe(viewLifecycleOwner, Observer {
            it?.let { adapter?.setTodos(it) }
        })
        binding.recyclerView.adapter = adapter
        var a = 0
        fab.setOnClickListener {
            a++
            todoViewModel.insert(Todo(0, 111, "$a", "$a", a % 2 == 0))
        }
        binding.lifecycleOwner = this
    }
}

package asiantech.internship.winter.savedata.ui.todo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import asiantech.internship.summer.R
import asiantech.internship.summer.databinding.FragmentTodoBinding
import asiantech.internship.winter.savedata.db.TodoDatabase
import asiantech.internship.winter.savedata.ui.ViewModelFactory

class TodoFragment : Fragment() {

    private lateinit var binding: FragmentTodoBinding
    private lateinit var todoViewModel: TodoViewModel
    private val idUser = 111L

    companion object {
        fun newInstance() = TodoFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todo, container, false)
        Log.d("aaa", "onCreateView")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(activity?.application)
        val dataSource = TodoDatabase.getInstance(application)
        val viewModelFactory = ViewModelFactory(dataSource, application)
        todoViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(TodoViewModel::class.java)
        binding.todoViewModel = todoViewModel
        val adapter = TodoAdapter(TodoListener { todo, viewId ->
            when (viewId) {
                R.id.btnDelete -> {
                    todoViewModel.deleteTodo(todo.idTodo)
                    context?.let {
                        AlertDialog.Builder(it).apply {
                            setTitle("Alert")
                            setMessage("Do you want delete this todo?")
                            setNegativeButton("No") { _, _ ->
                            }
                            setPositiveButton("Yes") { _, _ ->
                                todoViewModel.deleteTodo(todo.idTodo)
                                Toast.makeText(context, "Todo ${todo.title} was deleted!", Toast.LENGTH_LONG).show()
                            }
                        }.create().show()
                    }
                }
                R.id.btnEdit -> Toast.makeText(context, "$todo edit ", Toast.LENGTH_LONG).show()
                R.id.btnDone -> {
                    todoViewModel.updateCompletedById(todo.idTodo)
                    Toast.makeText(context, "Todo ${todo.title} was completed!", Toast.LENGTH_LONG).show()

                }
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

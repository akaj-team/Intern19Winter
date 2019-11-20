package asiantech.internship.winter.savedata.ui.todo

import android.content.Context
import android.os.Bundle
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
import androidx.navigation.fragment.findNavController
import asiantech.internship.summer.R
import asiantech.internship.summer.databinding.FragmentTodoBinding
import asiantech.internship.winter.savedata.db.TodoDatabase
import asiantech.internship.winter.savedata.ui.ViewModelFactory
import asiantech.internship.winter.savedata.ui.home.HomeFragmentDirections

class TodoFragment : Fragment() {

    private lateinit var binding: FragmentTodoBinding
    private lateinit var todoViewModel: TodoViewModel
    private var idUser = 0L

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todo, container, false)
        val application = requireNotNull(activity?.application)
        val dataSource = TodoDatabase.getInstance(application)
        val viewModelFactory = ViewModelFactory(dataSource, application)
        todoViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(TodoViewModel::class.java)
        binding.todoViewModel = todoViewModel

        activity?.getPreferences(Context.MODE_PRIVATE)?.apply {
            idUser = getLong(getString(R.string.pref_id_user), 0L)
        }

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
                R.id.btnEdit -> {
                    todoViewModel.onEditClicked(todo.idTodo)
                }
                R.id.btnDone -> {
                    todoViewModel.updateCompletedById(todo.idTodo)
                    Toast.makeText(context, "Todo ${todo.title} was completed!", Toast.LENGTH_LONG).show()
                }
            }
        })

        todoViewModel.getTodosByIdUser(idUser).observe(viewLifecycleOwner, Observer {
            it?.let { adapter.setTodos(it) }
        })

        todoViewModel.navigateToEditTodo.observe(this, Observer { idTodo ->
            idTodo?.let {
                this.findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToEditTodoFragment(idTodo))
                todoViewModel.onEditTodoNavigated()
            }
        })

        binding.recyclerView.adapter = adapter
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_newTodoFragment)
        }
    }
}

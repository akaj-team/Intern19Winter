package asiantech.internship.winter.savedata.ui.edittodo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import asiantech.internship.summer.R
import asiantech.internship.summer.databinding.FragmentEditTodoBinding
import asiantech.internship.winter.savedata.db.TodoDatabase

class EditTodoFragment : Fragment() {

    private lateinit var editTodoViewModel: EditTodoViewModel
    private lateinit var binding: FragmentEditTodoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_todo, container, false)
        val application = requireNotNull(activity?.application)
        val dataSource = TodoDatabase.getInstance(application)
        val idTodo = arguments?.let { EditTodoFragmentArgs.fromBundle(it).idTodo }
        Log.d("aaa", "$idTodo id todo")
        val viewModelFactory = idTodo?.let { EditTodoViewModelFactory(it, dataSource, application) }
        val editTodoViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(EditTodoViewModel::class.java)
        binding.editTodoViewModel = editTodoViewModel

        binding.btnSave.setOnClickListener {
            editTodoViewModel.getTodo().observe(this, Observer {
                it.copy(title = binding.edtTitle.text.toString(), description = binding.edtDescription.text.toString())
                        .let { todo ->
                            editTodoViewModel.update(todo)
                        }
            })
            findNavController().navigate(R.id.action_editTodoFragment_to_homeFragment)
        }

        binding.lifecycleOwner = this

        return binding.root
    }
}

package asiantech.internship.winter.savedata.db.newtodo


import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import asiantech.internship.summer.R
import asiantech.internship.summer.databinding.FragmentNewTodoBinding
import asiantech.internship.winter.savedata.db.TodoDatabase
import asiantech.internship.winter.savedata.db.todo.Todo
import asiantech.internship.winter.savedata.ui.ViewModelFactory


class NewTodoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil
                .inflate<FragmentNewTodoBinding>(inflater, R.layout.fragment_new_todo, container, false)
        val application = requireNotNull(activity?.application)
        val dataSource = TodoDatabase.getInstance(application)
        val viewModelFactory = ViewModelFactory(dataSource, application)
        val newTodoViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(NewTodoViewModel::class.java)
        binding.newTodoViewModel = newTodoViewModel


        binding.btnAdd.setOnClickListener {
            when {
                TextUtils.isEmpty(binding.edtTitle.text) -> {
                    Toast.makeText(context, "Please enter todo title!", Toast.LENGTH_LONG).show()
                }
                TextUtils.isEmpty(binding.edtDescription.text) -> {
                    Toast.makeText(context, "Please enter todo description!", Toast.LENGTH_LONG).show()
                }
                else -> {
                    var idUser = 0L
                    activity?.getPreferences(Context.MODE_PRIVATE)?.apply {
                        idUser = getLong(getString(R.string.pref_id_user), 0L)
                    }
                    newTodoViewModel.insert(Todo(0, idUser, binding.edtTitle.text.toString(),
                            binding.edtDescription.text.toString(), false))
                    findNavController().navigate(R.id.action_newTodoFragment_to_homeFragment)
                }
            }
        }

        return binding.root
    }
}

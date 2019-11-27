package asiantech.internship.summer.savedatabase.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import kotlinx.android.synthetic.main.fragment_edit_todo.*

class EditToDoFragment : Fragment(){

    companion object{
        private const val ARG_TODO_NAME = "todoName"
        private const val ARG_TODO_CONTENT = "todoContent"
        fun newInstance(toDoName:String,todoContent:String) = EditToDoFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_TODO_NAME,toDoName)
                putString(ARG_TODO_CONTENT,todoContent)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_todo,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        edtTodoNew.setText(arguments?.getString(ARG_TODO_NAME))
        edtTodo.setText(arguments?.getString(ARG_TODO_CONTENT))
    }
}

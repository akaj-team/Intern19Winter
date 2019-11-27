package asiantech.internship.summer.savedatabase.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.summer.savedatabase.database.DBHandling
import asiantech.internship.summer.savedatabase.database.TodoModel
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_new_todo.*

class NewTodoFragment : Fragment() {

    companion object {
        fun newInstance() = NewTodoFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_new_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        btnDone.setOnClickListener {
            val todoHandling = context?.let { it1 -> DBHandling(it1) }
            val newTodo = edtTodoNew.text.toString()
            val inputTodo = edtTodo.text.toString()
            if (newTodo == "" || inputTodo == "") {
                Toast.makeText(activity, "Enter full information", Toast.LENGTH_SHORT).show()
            } else {
                todoHandling?.insertTodo(TodoModel(todoName = newTodo,todoContent = inputTodo))
                Toast.makeText(activity, "Done", Toast.LENGTH_SHORT).show()

                fragmentManager?.beginTransaction()?.
                        replace(R.id.frameLayoutActivity, ViewPagerFragment.newInstance())?.
                        addToBackStack(null)?.
                        commit()
            }
        }
    }
}

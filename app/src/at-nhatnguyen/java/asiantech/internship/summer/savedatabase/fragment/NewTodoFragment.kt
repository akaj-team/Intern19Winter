package asiantech.internship.summer.savedatabase.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.summer.savedatabase.database.DBHandling
import asiantech.internship.summer.savedatabase.database.TodoModel
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_new_todo.*

class NewTodoFragment :Fragment(){

    companion object{
        fun newInstance() = NewTodoFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_new_todo,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        btnDone.setOnClickListener {
            val todoHandling = context?.let { it1 -> DBHandling(it1) }
            val newTodo = edtTodoNew.text.toString()

            val result = todoHandling?.insertTodo(TodoModel(todoName = newTodo))
            Log.d("hhh","$result")
            Toast.makeText(activity,"Done",Toast.LENGTH_SHORT).show()

            fragmentManager?.beginTransaction()?.
                    replace(R.id.frameLayout,ItemToDoFragment.newInstance())?.
                    commit()
        }


    }
}
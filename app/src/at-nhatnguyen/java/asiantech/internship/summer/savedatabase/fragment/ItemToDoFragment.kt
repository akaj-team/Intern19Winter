package asiantech.internship.summer.savedatabase.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.R
import asiantech.internship.summer.savedatabase.adapter.RowTodoAdapter
import asiantech.internship.summer.savedatabase.database.DBHandling
import asiantech.internship.summer.savedatabase.database.ItemTodoOnclick
import asiantech.internship.summer.savedatabase.database.TodoModel
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_item_todo.*

class ItemToDoFragment : Fragment() {

    companion object {
        private const val ARG_USER_ID = "userId"
        fun newInstance(userId: Int) = ItemToDoFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_USER_ID, userId)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_item_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = arguments?.getInt(ARG_USER_ID)
        val todoHandling = DBHandling(requireContext())
        val iTemTodo = todoHandling.readTodo(userId!!)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val adapter = context?.let { RowTodoAdapter(iTemTodo, it) }
        recyclerView.adapter = adapter

        floatingActionButton.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.frameLayoutActivity, NewTodoFragment.newInstance(userId))?.commit()
        }
        adapter?.let { setOnClickIconEditToDo(it) }
    }

    private fun setOnClickIconEditToDo(adapter: RowTodoAdapter) {
        adapter.onClick(object : ItemTodoOnclick {
            override fun doneTodoOnclick(todoModel: TodoModel) {
                Toast.makeText(activity,"nnnnnnnnnnnnnnnnn",Toast.LENGTH_SHORT).show()
               // imgIconDone.setImageResource(R.drawable.ic_mode_edit_black)
            }

            override fun editTodoOnclick(todoModel: TodoModel) {
                val userId = arguments?.getInt(ARG_USER_ID)
                if (todoModel.todoName != "") {
                    fragmentManager?.beginTransaction()?.replace(R.id.frameLayoutActivity, EditTodoFragment.newInstance(todoModel.todoName, todoModel.todoContent, userId!!, todoModel.todoId))?.
                            addToBackStack(null)?.
                            commit()
                }
            }
        })
    }
}

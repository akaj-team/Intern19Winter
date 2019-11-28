package asiantech.internship.summer.savedatabase.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            override fun onClick(onclick: TodoModel) {
                val userId = arguments?.getInt(ARG_USER_ID)
                if (onclick.todoName != "") {
                    fragmentManager?.beginTransaction()?.replace(R.id.frameLayoutActivity, EditTodoFragment.newInstance(onclick.todoName, onclick.todoContent, userId!!, onclick.todoId))?.addToBackStack(null)?.commit()
                }
            }
        })
    }
}

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

        fun newInstance() = ItemToDoFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_item_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val todoHandling = DBHandling(requireContext())
        val iTemTodo = todoHandling.readTodo()
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val adapter = context?.let { RowTodoAdapter(iTemTodo, it) }
        recyclerView.adapter = adapter

        floatingActionButton.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.frameLayoutActivity, NewTodoFragment.newInstance())?.commit()
        }
        adapter?.let { setOnClickIconEditToDo(it) }
    }

    private fun setOnClickIconEditToDo(adapter: RowTodoAdapter) {
        adapter.onClick(object : ItemTodoOnclick {
            override fun onClick(onclick: TodoModel) {
                if (onclick.todoName != "") {
                    fragmentManager?.beginTransaction()?.replace(R.id.frameLayoutActivity, EditTodoFragment.newInstance(onclick.todoName, onclick.todoContent))?.addToBackStack(null)?.commit()
                }
            }
        })
    }
}

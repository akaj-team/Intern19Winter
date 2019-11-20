package asiantech.internship.summer.savedatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import asiantech.internship.summer.savedatabase.database.TodoModel

class RowTodoAdapter(private val mItemToDo: MutableList<TodoModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item_todo, parent, false)
        return TodoItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mItemToDo.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TodoItemViewHolder) {
            holder.onBindData(position)
        }
    }

    inner class TodoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNameTodo: TextView = itemView.findViewById(R.id.tvTodoName)
        fun onBindData(position: Int) {
            val itemTodo = mItemToDo[position]
            tvNameTodo.text = itemTodo.todoName
        }
    }
}

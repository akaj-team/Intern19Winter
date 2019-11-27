package asiantech.internship.summer.savedata.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import asiantech.internship.summer.savedata.entity.Todo

class TodoListHomeDoneAdapter(private var todoLists: MutableList<Todo>) :
        RecyclerView.Adapter<TodoListHomeDoneAdapter.HomeTodoListDoneViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTodoListDoneViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item_todo_done, parent, false)
        return HomeTodoListDoneViewHolder(view)
    }

    override fun getItemCount() = todoLists.size

    override fun onBindViewHolder(holder: HomeTodoListDoneViewHolder, position: Int) {
        holder.onBindData(position)
    }

    inner class HomeTodoListDoneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBindData(position: Int) {
            val todoItem = todoLists[position]
            val tvTodoContent = itemView.findViewById<TextView>(R.id.tvTodoContent)
            tvTodoContent.text = todoItem.title
        }
    }
}

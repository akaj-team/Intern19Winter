package asiantech.internship.summer.savedata.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import asiantech.internship.summer.savedata.entity.Todo

class TodoListHomeDoneAdapter(mTodoLists: MutableList<Todo>) :
        RecyclerView.Adapter<TodoListHomeDoneAdapter.HomeTodoListDoneViewHolder>() {

    private var mTodoListDones = mutableListOf<Todo>()

    init {
        mTodoLists.forEach {
            if (it.status == 1) {
                mTodoListDones.add(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTodoListDoneViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item_todo_done, parent, false)
        return HomeTodoListDoneViewHolder(view)
    }

    override fun getItemCount() = mTodoListDones.size

    override fun onBindViewHolder(holder: HomeTodoListDoneViewHolder, position: Int) {
        holder.onBindData(position)
    }

    inner class HomeTodoListDoneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBindData(position: Int) {
            val todoItem = mTodoListDones[position]
            val tvTodoContent = itemView.findViewById<TextView>(R.id.tvTodoContent)
            tvTodoContent.text = todoItem.title
        }
    }
}

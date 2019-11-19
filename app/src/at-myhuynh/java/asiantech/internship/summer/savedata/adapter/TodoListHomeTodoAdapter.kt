package asiantech.internship.summer.savedata.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import asiantech.internship.summer.savedata.entity.Todo
import asiantech.internship.summer.savedata.interfaces.TodoItemOnclick

class TodoListHomeTodoAdapter(private val mTodoLists: MutableList<Todo>) :
        RecyclerView.Adapter<TodoListHomeTodoAdapter.HomeTodoViewHolder>() {

    var mTodoItemOnClick: TodoItemOnclick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item_todo, parent, false)
        return HomeTodoViewHolder(view)
    }

    override fun getItemCount() = mTodoLists.size

    override fun onBindViewHolder(holder: HomeTodoViewHolder, position: Int) {
        holder.onBindData(position)
    }

    fun setTodoItemOnClick(todoItemOnclick: TodoItemOnclick) {
        mTodoItemOnClick = todoItemOnclick
    }

    inner class HomeTodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBindData(position: Int) {
            val todoItem = mTodoLists[position]
            val tvTodoContent = itemView.findViewById<TextView>(R.id.tvTodoContent)
            val checkBoxStatus = itemView.findViewById<CheckBox>(R.id.cbStatus)
            val imgEdit = itemView.findViewById<ImageView>(R.id.imgEdit)
            val imgDelete = itemView.findViewById<ImageView>(R.id.imgDelete)

            tvTodoContent.text = todoItem.title
            if (todoItem.status == 1) {
                checkBoxStatus.isChecked = true
            }

            imgEdit.setOnClickListener {
                mTodoItemOnClick?.editTodoOnClick(todoItem)
            }

            imgDelete.setOnClickListener {
                mTodoItemOnClick?.deleteTodoOnClick(todoItem)
            }

            checkBoxStatus.setOnClickListener {
                mTodoItemOnClick?.checkBoxTodoOnClick(todoItem)
            }
        }
    }
}

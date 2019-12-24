package asiantech.internship.summer.retrofitapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R

class ListAdapter(private val listTodo: MutableList<TodoModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onClick: Onclick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_todo, parent, false)
        return TodoItemViewHolder(view)
    }

    override fun getItemCount() = listTodo.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TodoItemViewHolder) {
            holder.onBindData(position)
        }
    }

    inner class TodoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTodoName: TextView = itemView.findViewById(R.id.tvTodoName)
        private val imgEdit: ImageView = itemView.findViewById(R.id.imgEditTodo)
        private val imgDelete: ImageView = itemView.findViewById(R.id.imgDeleteTodo)
        fun onBindData(position: Int) {
            val todoModel = listTodo[position]
            tvTodoName.text = todoModel.todoName

            imgDelete.setOnClickListener {
                onClick?.iconDeleteOnclick(todoModel)
                listTodo.remove(todoModel)
                notifyDataSetChanged()
            }

            imgEdit.setOnClickListener {
                onClick?.iconEditTodoOnclick(todoModel)
            }
        }
    }

    fun click(onClick: Onclick) {
        this.onClick = onClick
    }

    interface Onclick {
        fun iconDeleteOnclick(todoModel: TodoModel)
        fun iconEditTodoOnclick(todoModel: TodoModel)
    }
}

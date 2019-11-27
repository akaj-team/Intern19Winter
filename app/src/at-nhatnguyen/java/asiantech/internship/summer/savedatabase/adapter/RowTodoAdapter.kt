package asiantech.internship.summer.savedatabase.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import asiantech.internship.summer.savedatabase.database.DBHandling
import asiantech.internship.summer.savedatabase.database.ItemTodoOnclick
import asiantech.internship.summer.savedatabase.database.TodoModel

class RowTodoAdapter(private val mItemToDo: MutableList<TodoModel>,private val mContext: Context) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var itemOnClick : ItemTodoOnclick? = null
    val todoDBHandling = DBHandling(mContext)

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

     fun onClick(itemOnClick : ItemTodoOnclick){
        this.itemOnClick = itemOnClick
    }

    inner class TodoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNameTodo: TextView = itemView.findViewById(R.id.tvTodoName)
        private val imgDelete:ImageView = itemView.findViewById(R.id.imgIconDelete)
        private val imgEdit:ImageView = itemView.findViewById(R.id.imgIconEdit)
        fun onBindData(position: Int) {
            val itemTodo = mItemToDo[position]
            tvNameTodo.text = itemTodo.todoName

            imgDelete.setOnClickListener {
               Toast.makeText(mContext,"Deleted ${itemTodo.todoName}",Toast.LENGTH_SHORT).show()
                todoDBHandling.deleteTodo(TodoModel(itemTodo.todoName,itemTodo.todoContent))
                mItemToDo.remove(itemTodo)
                notifyDataSetChanged()
            }

            imgEdit.setOnClickListener {
                itemOnClick?.onClick(itemTodo)
            }
        }
    }
}

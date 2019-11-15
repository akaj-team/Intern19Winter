package asiantech.internship.winter.savedata.ui.done

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import asiantech.internship.winter.savedata.db.todo.Todo

class DoneAdapter internal constructor(private val mContext: Context) :
        RecyclerView.Adapter<DoneAdapter.DoneViewHolder>() {

    private var todos = emptyList<Todo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoneViewHolder {
        return DoneViewHolder(
                LayoutInflater.from(mContext).inflate(
                        R.layout.item_list_done,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount() = todos.size

    override fun onBindViewHolder(holder: DoneViewHolder, position: Int) {
        holder.onBind(position)
    }

    internal fun setTodos(todos: List<Todo>) {
        this.todos = todos
        notifyDataSetChanged()
    }

    inner class DoneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)

        fun onBind(position: Int) {
            val todo = todos[position]
            tvTitle.text = todo.title
            tvDescription.text = todo.description
        }
    }
}

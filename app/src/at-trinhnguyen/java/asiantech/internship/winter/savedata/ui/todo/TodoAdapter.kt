package asiantech.internship.winter.savedata.ui.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import asiantech.internship.summer.databinding.ItemListTodoBinding
import asiantech.internship.winter.savedata.db.todo.Todo

class TodoAdapter internal constructor(private val mClickListener: TodoListener) :
        RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var todos = emptyList<Todo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
                ItemListTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = todos.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.onBind(position, mClickListener)
    }

    inner class TodoViewHolder constructor(val binding: ItemListTodoBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int, clickListener: TodoListener) {

            val todo = todos[position]
            binding.todo = todo
            binding.tvTitle.text = todo.title
            binding.tvDescription.text = todo.description
            binding.btnDelete.setOnClickListener {
                clickListener.onClick(todo, R.id.btnDelete)
            }
            binding.btnEdit.setOnClickListener {
                clickListener.onClick(todo, R.id.btnEdit)
            }
            binding.btnDone.setOnClickListener {
                clickListener.onClick(todo, R.id.btnDone)
            }
            binding.clickListener = clickListener
        }
    }

    internal fun setTodos(todos: List<Todo>) {
        this.todos = todos
        notifyDataSetChanged()
    }

}

class TodoListener(val clickListener: (todo: Todo, view: Int) -> Unit) {
    fun onClick(todo: Todo, view: Int) = clickListener(todo, view)
}

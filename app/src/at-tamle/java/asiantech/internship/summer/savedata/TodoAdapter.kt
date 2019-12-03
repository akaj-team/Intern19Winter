package asiantech.internship.summer.savedata

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AlertDialog
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-tamle`.edit_todo.view.*
import kotlinx.android.synthetic.`at-tamle`.item_todo.view.*

class TodoAdapter(var context: Context, var dataTodos: MutableList<DataTodo>) :
    RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    private var mTodo: Todo? = null
    private var mPreference: Preference? = null

    init {
        mTodo = Todo(context)
        mPreference = Preference(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mTvTodo.setText(dataTodos.get(position).todo)
    }

    override fun getItemCount(): Int {
        return dataTodos.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTvTodo = itemView.tvTodo
        val mImgRemove = itemView.iconBin
        val mImgEdit = itemView.iconEdit
        val mImgCheck = itemView.iconCheck

        init {
            mImgCheck.setOnClickListener {
                val currDataTodo: DataTodo = dataTodos.get(adapterPosition)
                val idUser = mPreference?.getInt(Preference.ID_USER)
                val result = mTodo?.updateStatus(currDataTodo.id, idUser!!.toInt(), 1)
                if (result == true) {
                    dataTodos.removeAt(adapterPosition)
                    notifyDataSetChanged()
                }
            }
            mImgRemove.setOnClickListener {
                val result: Boolean = mTodo?.delete(dataTodos.get(adapterPosition)) as Boolean
                dataTodos.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
                Snackbar.make(
                    itemView,
                    if (result) R.string.xoa_thanh_cong else R.string.xoa_that_bai,
                    Snackbar.LENGTH_SHORT
                ).setAction(
                    R.string.action_ok, {}).show()
            }
            mImgEdit.setOnClickListener {
                val currTodo = dataTodos.get(adapterPosition)
                val myView = LayoutInflater.from(context).inflate(R.layout.edit_todo, null)
                val edtNewTodo_edit = myView.edtNewTodo
                edtNewTodo_edit.hint = currTodo.todo

                AlertDialog.Builder(context)
                    .setView(myView)
                    .setNegativeButton(
                        R.string.dialog_confirm,
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            val newTodo = edtNewTodo_edit.text.toString().trim()
                            if (newTodo.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    R.string.ban_chua_nhap_todo,
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@OnClickListener
                            }
                            currTodo.todo = newTodo
                            val result = mTodo?.update(currTodo) as Boolean
                            notifyItemChanged(adapterPosition)
                            Snackbar.make(
                                itemView,
                                if (result) R.string.cap_nhat_thanh_cong else R.string.cap_nhat_that_bai,
                                Snackbar.LENGTH_SHORT
                            ).setAction(
                                R.string.action_ok, {}).show()
                        })
                    .setPositiveButton(
                        R.string.dialog_dismiss,
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            Snackbar.make(itemView, R.string.dialog_dismiss, Snackbar.LENGTH_SHORT)
                                .setAction(
                                    R.string.action_ok, {}).show()
                        })
                    .show()
            }
        }
    }
}

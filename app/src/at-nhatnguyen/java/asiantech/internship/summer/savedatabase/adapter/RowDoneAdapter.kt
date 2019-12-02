package asiantech.internship.summer.savedatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import asiantech.internship.summer.savedatabase.items.ItemDone

class RowDoneAdapter(private val mName: MutableList<ItemDone>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_done, parent, false)
        return RowDoneItemViewHolder(view)
    }

    override fun getItemCount() = mName.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RowDoneItemViewHolder) {
            holder.onBindData(position)
        }
    }

    inner class RowDoneItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvDone: TextView = itemView.findViewById(R.id.tvDone)
        fun onBindData(position: Int) {
            val it = mName[position]
            tvDone.text = it.mNameTodoDone
        }
    }
}

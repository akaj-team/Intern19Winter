package asiantech.internship.summer.savedata

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-tamle`.item_done.view.*

class DoneAdapter(var context: Context, var dataTodos: MutableList<DataTodo>) : RecyclerView.Adapter<DoneAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_done, parent, false))
    }

    override fun getItemCount(): Int {
        return dataTodos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvDone.setText(dataTodos.get(position).todo)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tvDone = itemView.tvDone
    }
}

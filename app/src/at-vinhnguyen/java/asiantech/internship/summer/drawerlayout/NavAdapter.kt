package asiantech.internship.summer.drawerlayout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import de.hdodenhof.circleimageview.CircleImageView

class NavAdapter(val listItemNav: MutableList<ItemNav?>, val context: Context, val listener: RecyclerViewClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_ITEM = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType.equals(VIEW_TYPE_ITEM)) {
            return ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_nav, parent, false))
        }
        return HeaderViewHolder(LayoutInflater.from(context).inflate(R.layout.item_nav_header, parent, false), listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bindView(position)
        }
    }

    override fun getItemViewType(position: Int): Int = if (listItemNav[position] == null) {
        VIEW_TYPE_HEADER
    } else VIEW_TYPE_ITEM

    override fun getItemCount(): Int = listItemNav.size

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mTextViewItem = itemView.findViewById<TextView>(R.id.tvItemNav)
        fun bindView(position: Int) {
            val itemNav = listItemNav.get(position)
            itemNav?.let {
                mTextViewItem.text = it.name
                mTextViewItem.setCompoundDrawablesWithIntrinsicBounds(it.drawableIcon, 0, 0, 0)
            }
            itemView.setOnClickListener { Toast.makeText(context, mTextViewItem.text, Toast.LENGTH_SHORT).show() }
        }
    }

    inner class HeaderViewHolder(itemView: View, listener: RecyclerViewClickListener) : RecyclerView.ViewHolder(itemView) {
        var imageProfile: CircleImageView

        init {
            imageProfile = itemView.findViewById(R.id.imgProfile)
            imageProfile.setOnClickListener { listener.onImageClicked(imageProfile) }
        }
    }
}

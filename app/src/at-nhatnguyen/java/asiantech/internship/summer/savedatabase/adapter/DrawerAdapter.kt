package asiantech.internship.summer.savedatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import asiantech.internship.summer.savedatabase.items.ItemDrawerBody
import asiantech.internship.summer.savedatabase.items.ItemDrawerHeader

class DrawerAdapter(private val mItemHeader: MutableList<ItemDrawerHeader>, private val mItemBody: MutableList<ItemDrawerBody>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewType(val type: Int) {
        TYPE_ONE(0), TYPE_TWO(1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.TYPE_ONE.type -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item_header_drawer, parent, false)
                ItemHeaderViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item_body_drawer, parent, false)
                ItemBodyViewHolder(view)
            }
        }
    }

    override fun getItemCount() = mItemHeader.size + mItemBody.size

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            in 0 until mItemHeader.size -> ViewType.TYPE_ONE.type
            else -> ViewType.TYPE_TWO.type
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.apply {
            when (holder) {
                is ItemHeaderViewHolder -> holder.onBindData(position)
                is ItemBodyViewHolder -> holder.onBindData(position - (mItemHeader.size))
            }
        }
    }

    inner class ItemHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgAvatar: ImageView = itemView.findViewById(R.id.imgAvatar)
        private val tvEmail: TextView = itemView.findViewById(R.id.tvEmail)
        fun onBindData(position: Int) {
            val it = mItemHeader[position]
            imgAvatar.setImageResource(it.mAvatar)
            tvEmail.text = it.mEmail

        }
    }

    inner class ItemBodyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val icon: ImageView = itemView.findViewById(R.id.imgIcon)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        fun onBindData(position: Int) {
            val it = mItemBody[position]
            icon.setImageResource(it.mIcon)
            tvTitle.text = it.mTitle

        }


    }

}
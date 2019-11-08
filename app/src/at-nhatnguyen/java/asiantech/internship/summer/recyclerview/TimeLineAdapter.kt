package asiantech.internship.summer.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R

class TimeLineAdapter(private val timeLineItems: MutableList<TimeLineItem?>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_ITEM = 0
        private const val VIEW_TYPE_LOADING = 1
    }

    override fun getItemCount() = timeLineItems.size
    override fun getItemViewType(position: Int): Int {
        return if (timeLineItems[position] == null) {
            VIEW_TYPE_LOADING
        } else {
            VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
            TimeLineViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_progress, parent, false)
            LoadingViewHolder(view)
        }

    }

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TimeLineViewHolder) {
            holder.onBindData(position)
        }
    }

    inner class TimeLineViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var imgFood: ImageView = view.findViewById(R.id.imgFood)
        private var imgLike: ImageView = view.findViewById(R.id.imgLike)
        private var tvCountLike: TextView = view.findViewById(R.id.tvCountLike)
        private var tvNickName: TextView = view.findViewById(R.id.tvNickName)
        fun onBindData(position: Int) {
            val it = timeLineItems[position]
            it?.let {
                tvNickName.text = it.nickName
                imgFood.setImageResource(it.photo)
                tvCountLike.text = (it.countLike).toString()
                imgLike.setImageResource(if (it.isLike) {
                    R.drawable.ic_favorite_border
                } else {
                    R.drawable.ic_favorite_red
                })
                imgLike.setOnClickListener { _ ->
                    if (it.isLike) {
                        it.countLike++
                        it.isLike = false
                        tvCountLike.text = (it.countLike).toString()
                    } else {
                        it.countLike--
                        it.isLike = true
                        tvCountLike.text = (it.countLike).toString()
                    }
                    imgLike.setImageResource(if (it.isLike) {
                        R.drawable.ic_favorite_border
                    } else {
                        R.drawable.ic_favorite_red
                    }
                    )
                }
            }
        }
    }
    fun clear() {
        timeLineItems.clear()
        notifyDataSetChanged()
    }
}

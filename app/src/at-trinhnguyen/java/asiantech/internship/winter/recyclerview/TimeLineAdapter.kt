package asiantech.internship.winter.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import com.bumptech.glide.Glide

class TimeLineAdapter(private val mTimeLineItems: MutableList<TimeLineItem?>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemClick: ((Int) -> Unit)? = null

    companion object {
        private const val VIEW_TYPE_ITEM = 0
        private const val VIEW_TYPE_LOADING = 1
    }

    override fun getItemCount() = mTimeLineItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_time_line, parent, false)
            TimeLineViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_progress_loading, parent, false)
            LoadingViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TimeLineViewHolder) {
            holder.onBindData(position)
        }
    }

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class TimeLineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBindData(position: Int) {
            val timeLineItem = mTimeLineItems[position]

            val imgAvatar = itemView.findViewById<ImageView>(R.id.imgAvatar)
            val tvNickNameTop = itemView.findViewById<TextView>(R.id.tvNickNameTop)
            val tvNickName = itemView.findViewById<TextView>(R.id.tvNickName)
            val imgTimeLine = itemView.findViewById<ImageView>(R.id.imgTimeLine)
            val imgLike = itemView.findViewById<ImageView>(R.id.imgLike)
            val tvCountLike = itemView.findViewById<TextView>(R.id.tvCountLike)

            timeLineItem?.let {
                Glide.with(itemView).load(it.imgAvatar).circleCrop().into(imgAvatar)
                Glide.with(itemView).load(it.imgTimeLine).into(imgTimeLine)
                tvNickNameTop.text = it.nickname
                tvNickName.text = it.nickname
                tvCountLike.text = it.countLike.toString()
                if (it.isLiked) {
                    imgLike.setImageResource(R.drawable.ic_heart_bold_active)
                } else {
                    imgLike.setImageResource(R.drawable.ic_heart_bold)
                }

                imgLike.setOnClickListener {
                    onItemClick?.invoke(position)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (mTimeLineItems[position] == null) VIEW_TYPE_LOADING
        else VIEW_TYPE_ITEM
    }

    fun clear() {
        mTimeLineItems.clear()
        notifyDataSetChanged()
    }

    fun addAll(list: List<TimeLineItem?>) {
        mTimeLineItems.addAll(list)
        notifyDataSetChanged()
    }
}

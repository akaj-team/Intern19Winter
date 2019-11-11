package asiantech.internship.summer.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import java.util.*

class TimelineAdapter(private val mTimeLines: MutableList<TimelineItem>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var timelineOnClickListener: FavoriteOnClickListener? = null

    companion object {
        var mItemsDisplay = 10
        private const val VIEW_TYPE_ITEM = 0
        private const val VIEW_TYPE_LOADING = 1
        private lateinit var timeLineDisplay: MutableList<TimelineItem>
    }

    init {
        timeLineDisplay = mTimeLines.subList(0, mItemsDisplay)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM -> {
                val view = LayoutInflater
                        .from(parent.context).inflate(R.layout.row_item, parent, false)
                TimelineViewHolder(view)
            }
            VIEW_TYPE_LOADING -> {
                val view = LayoutInflater
                        .from(parent.context).inflate(R.layout.item_loading, parent, false)
                LoadingViewHolder(view)
            }
            else -> throw RuntimeException("The type has to be ONE or TWO")
        }
    }

    override fun getItemCount() = timeLineDisplay.size

    override fun getItemViewType(position: Int): Int {
        val item = timeLineDisplay[position]
        if (item.type == VIEW_TYPE_ITEM) {
            return VIEW_TYPE_ITEM
        } else if (item.type == VIEW_TYPE_LOADING && mItemsDisplay != mTimeLines.size) {
            return VIEW_TYPE_LOADING
        }
        throw Exception("Error, unknown view type")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == VIEW_TYPE_ITEM) {
            holder as TimelineViewHolder
            holder.onBindData(position)
        }
    }

    fun isLoading(): Boolean {
        return if (timeLineDisplay.isEmpty()) {
            true
        } else {
            timeLineDisplay.last().type == VIEW_TYPE_LOADING
        }
    }

    fun loadMore(lastTimeLine: Int) {
        mItemsDisplay = if (lastTimeLine + 10 < mTimeLines.size) {
            lastTimeLine + 10
        } else {
            mTimeLines.size
        }

        timeLineDisplay = mTimeLines.subList(0, mItemsDisplay)
        notifyDataSetChanged()
    }

    fun addFooter() {
        if (!isLoading()) {
            timeLineDisplay.add(TimelineItem("Footer", R.drawable.img_food_1, "", 0, false, 1))
            notifyItemInserted(timeLineDisplay.size)
        }
    }

    fun removeFooter() {
        if (isLoading()) {
            timeLineDisplay.removeAt(timeLineDisplay.size - 1)
            notifyItemRemoved(timeLineDisplay.size - 1)
        }
    }

    fun reset() {
        mItemsDisplay = 10
        timeLineDisplay.clear()
        notifyDataSetChanged()
    }

    fun setOnClickListener(timelineOnClickListener: FavoriteOnClickListener) {
        this.timelineOnClickListener = timelineOnClickListener
    }

    inner class TimelineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBindData(position: Int) {
            val imgAvatar: ImageView = itemView.findViewById(R.id.imgAvatar)
            val tvName: TextView = itemView.findViewById(R.id.tvName)
            val imgPicture: ImageView = itemView.findViewById(R.id.imgPicture)
            val tvNumFavorite: TextView = itemView.findViewById(R.id.tvNumFavorite)
            val tvNickname: TextView = itemView.findViewById(R.id.tvNickName)
            val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
            val imgFavorite: ImageView = itemView.findViewById(R.id.imgFavorite)
            val timelineItem = timeLineDisplay[position]

            imgAvatar.setImageResource(timelineItem.image)
            tvName.text = timelineItem.name
            imgPicture.setImageResource(timelineItem.image)
            tvNumFavorite.text = String.format(Locale.ENGLISH, "%d", timelineItem.like)
            tvNickname.text = timelineItem.name
            tvDescription.text = timelineItem.description
            imgFavorite.setOnClickListener {
                timelineOnClickListener?.onItemOnClick(timelineItem)
            }

            if (timelineItem.isLike) {
                imgFavorite.setImageResource(R.drawable.ic_favorite_red_a700_24dp)
            } else {
                imgFavorite.setImageResource(R.drawable.ic_favorite_border_black_24dp)
            }
        }
    }

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

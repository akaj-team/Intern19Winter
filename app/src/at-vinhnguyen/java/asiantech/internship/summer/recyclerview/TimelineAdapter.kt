package asiantech.internship.summer.recyclerview

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.`at-vinhnguyen`.item_loading.view.*
import kotlinx.android.synthetic.`at-vinhnguyen`.item_timeline.view.*
import kotlin.math.log

class TimelineAdapter(val timelineListItem: MutableList<TimelineItem>, val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_ITEM = 0
        const val VIEW_TYPE_LOADING = 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.imgProfilePicture.setImageResource(timelineListItem.get(position).profilePicture)
            holder.tvNameTop.text = timelineListItem.get(position).name
            holder.imgContent.setImageResource(timelineListItem.get(position).imageContent)
            if (!timelineListItem.get(position).isLike) {
                holder.imgLike.setImageResource(R.drawable.ic_favorite_border_black_24dp)
            } else {
                holder.imgLike.setImageResource(R.drawable.ic_favorite_red_24dp)
            }
            holder.imgLike.setOnClickListener {
                if (timelineListItem.get(position).isLike) {
                    holder.imgLike.setImageResource(R.drawable.ic_favorite_border_black_24dp)
                    timelineListItem.get(position).isLike = false
                    timelineListItem.get(position).likeCount--
                } else {
                    holder.imgLike.setImageResource(R.drawable.ic_favorite_red_24dp)
                    timelineListItem.get(position).isLike = true
                    timelineListItem.get(position).likeCount++
                }
                holder.tvLikeCount.text = timelineListItem.get(position).likeCount.toString()
            }
            holder.tvLikeCount.text = timelineListItem.get(position).likeCount.toString()
            holder.tvNameBottom.text = timelineListItem.get(position).name
            holder.tvDescription.text = timelineListItem[position].description
        } else if (holder is LoadingViewHolder) {
            //TODO something
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            if (viewType.equals(VIEW_TYPE_ITEM)) {
                ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_timeline, parent, false))
            } else {
                LoadingViewHolder(LayoutInflater.from(context).inflate(R.layout.item_timeline, parent, false))
            }

    override fun getItemViewType(position: Int): Int {
        return if (timelineListItem.get(position) != null) {
            VIEW_TYPE_LOADING
        } else {
            VIEW_TYPE_ITEM
        }
    }

    override fun getItemCount(): Int = timelineListItem.size

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProfilePicture: CircleImageView = itemView.imgProfilePicture
        val tvNameTop: TextView = itemView.tvNameTop
        val imgContent: ImageView = itemView.imgContent
        val imgLike: ImageView = itemView.imgLike
        val tvLikeCount: TextView = itemView.tvLikeCount
        val tvNameBottom: TextView = itemView.tvNameBottom
        val tvDescription: TextView = itemView.tvDescription
    }

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
}

package asiantech.internship.summer.recyclerview

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.`at-tamle`.item_loading.view.*
import kotlinx.android.synthetic.`at-tamle`.row_item.view.*

class TimelineItemAdapter(recyclerView: RecyclerView, var activity: Activity, var timelineItems: MutableList<TimelineItem?>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    internal class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var progressBar = itemView.pbLoadMore
    }

    val VIEW_ITEM_TYPE = 0
    val VIEW_LOADING_TYPE = 1
    internal var loadMore: FavoriteOnClickListener? = null
    var isLoading: Boolean = false
    var visibleThresold = 5
    var lastVisibleItem = 0
    var totalItemCount = 0

    init {
        val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                totalItemCount = linearLayoutManager.itemCount
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()
                if (!isLoading && totalItemCount <= lastVisibleItem + visibleThresold) {
                    if (loadMore != null) {
                        loadMore!!.onLoadMore()
                    }
                    isLoading = true
                }
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_ITEM_TYPE) {
            return ItemViewHolder(LayoutInflater.from(activity).inflate(R.layout.row_item, parent, false))
        }
        return LoadingViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_loading, parent, false))
    }

    override fun getItemCount(): Int {
        return timelineItems.size
    }

    internal class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.tvName
        val tvNumFavorite = itemView.tvNumFavorite
        val tvNickname = itemView.tvNickName
        val imgLike = itemView.imgFavorite
        val imgPicture: ImageView = itemView.imgPicture
        val tvDescription: TextView = itemView.tvDescription
        val imgAvatar: CircleImageView = itemView.imgAvatar
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            val currTimelineItem = timelineItems.get(position)
            holder.tvName.text = currTimelineItem!!.name
            holder.imgPicture.setImageResource(currTimelineItem.image)
            holder.imgAvatar.setImageResource(currTimelineItem.avatar)
            holder.tvNickname.text = currTimelineItem.name
            holder.tvDescription.text = currTimelineItem.description
            holder.tvNumFavorite.text = currTimelineItem.like.toString()
            holder.imgLike.setImageResource(if (currTimelineItem.isLike) R.drawable.ic_favorite_red else R.drawable.ic_favorite_border_black)
            holder.imgLike.setOnClickListener(View.OnClickListener {
                if (currTimelineItem.isLike) {
                    currTimelineItem.like = currTimelineItem.like - 1
                    currTimelineItem.isLike = false
                } else {
                    currTimelineItem.like = currTimelineItem.like + 1
                    currTimelineItem.isLike = true
                }
                notifyDataSetChanged()
            })
        } else if (holder is LoadingViewHolder) {
            holder.progressBar.isIndeterminate = true
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (timelineItems.get(position) == null) VIEW_LOADING_TYPE else VIEW_ITEM_TYPE
    }

    fun setLoaded() {
        isLoading = false
    }

    fun setLoadMore(loadMore: FavoriteOnClickListener) {
        this.loadMore = loadMore
    }
}

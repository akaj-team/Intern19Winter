package asiantech.internship.winter.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import com.bumptech.glide.Glide

class TimeLineAdapter(private val timeLineItems: MutableList<TimeLineItem>) :
        RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder>() {

    override fun getItemCount() = timeLineItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeLineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_time_line, parent, false)
        return TimeLineViewHolder(view)
    }

    override fun onBindViewHolder(holder: TimeLineViewHolder, position: Int) {
        holder.onBindData(position)
    }

    inner class TimeLineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBindData(position: Int) {
            val timeLineItem = timeLineItems[position]

            val imgAvatar = itemView.findViewById<ImageView>(R.id.imgAvatar)
            val tvNickName = itemView.findViewById<TextView>(R.id.tvNickName)
            val tvNickName2 = itemView.findViewById<TextView>(R.id.tvNickName2)
            val imgTimeLine = itemView.findViewById<ImageView>(R.id.imgTimeLine)
            val imgLike = itemView.findViewById<ImageView>(R.id.imgLike)
            val tvCountLike = itemView.findViewById<TextView>(R.id.tvCountLike)

            timeLineItem.let {
                Glide.with(itemView).load(it.imgAvatar).circleCrop().into(imgAvatar)
                Glide.with(itemView).load(it.imgTimeLine).into(imgTimeLine)
                tvNickName.text = it.nickname
                tvNickName2.text = it.nickname
                tvCountLike.text = it.countLike.toString()
                if (it.isLiked) {
                    imgLike.setImageResource(R.drawable.ic_favorite_filled_red)
                } else {
                    imgLike.setImageResource(R.drawable.ic_favorite_border)
                }

                imgLike.setOnClickListener { _ ->
                    if (it.isLiked) {
                        it.isLiked = false
                        it.countLike--
                        imgLike.setImageResource(R.drawable.ic_favorite_filled_red)
                    } else {
                        it.isLiked = true
                        it.countLike++
                        imgLike.setImageResource(R.drawable.ic_favorite_border)
                    }
                    notifyItemChanged(position)
                }
            }
        }
    }
}
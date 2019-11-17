package asiantech.internship.summer.drawerlayout

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-tamle`.item_menu.view.*

class MenuItemAdapter(val activity: Activity? = null, val menuItems: List<MenuItem>) : RecyclerView.Adapter<MenuItemAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_menu, parent, false))
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.imgIcon.setImageResource(menuItems[position].icon)
        holder.tvTitle.setText(menuItems[position].title)

        holder.setItemClickListener(object : ItemClickListener {
            override fun onClick(view: View, position: Int, isLongClick: Boolean) {
                if (!isLongClick) {
                    (activity as DrawerlayoutActivity).drawerLayout?.closeDrawer(GravityCompat.START)
                    activity.supportActionBar!!.setTitle(menuItems[position].title)
                    Toast.makeText(activity, menuItems[position].title, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var imgIcon = itemView.imgIcon
        var tvTitle = itemView.tvTitle
        internal var itemClickListener: ItemClickListener? = null

        init {
            itemView.setOnClickListener(this)
        }

        fun setItemClickListener(itemClickListener: ItemClickListener) {
            this.itemClickListener = itemClickListener
        }

        override fun onClick(view: View?) {
            view?.let { itemClickListener?.onClick(it, adapterPosition, false) }
        }
    }
}

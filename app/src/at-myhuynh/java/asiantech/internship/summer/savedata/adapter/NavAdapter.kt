package asiantech.internship.summer.savedata.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import asiantech.internship.summer.savedata.entity.NavItem
import asiantech.internship.summer.savedata.entity.User
import asiantech.internship.summer.savedata.interfaces.NavItemOnClick

class NavAdapter(val mUser: User, val mNavItems: MutableList<NavItem>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var navItemOnClick: NavItemOnClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.TYPE_ONE.type -> {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.row_item_todo_list_nav_header, parent, false)
                NavHeaderViewHolder(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.row_item_todo_list_nav_footer, parent, false)
                NavFooterViewHolder(view)
            }
        }
    }

    override fun getItemCount() = mNavItems.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.apply {
            when (holder) {
                is NavHeaderViewHolder -> holder.onBindData()
                is NavFooterViewHolder -> holder.onBindData(position - 1)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> ViewType.TYPE_ONE.type
            else -> ViewType.TYPE_TWO.type
        }
    }

    inner class NavHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBindData() {
            val imgAvatar = itemView.findViewById<ImageView>(R.id.imgAvatar)
            val tvUserName = itemView.findViewById<TextView>(R.id.tvUserName)

            imgAvatar.setImageResource(mUser.avatar)
            tvUserName.text = mUser.userName

        }
    }

    fun setOnclickNavItem(navItemOnClick: NavItemOnClick) {
        this.navItemOnClick = navItemOnClick
    }

    inner class NavFooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBindData(position: Int) {
            val imgIcon = itemView.findViewById<ImageView>(R.id.imgIcon)
            val tvNavItemName = itemView.findViewById<TextView>(R.id.tvNavItemName)
            val navItem = mNavItems[position]

            imgIcon.setImageResource(navItem.mIcon)
            tvNavItemName.text = navItem.mTitle

            itemView.setOnClickListener {
                navItemOnClick?.onClick(navItem)
            }
        }
    }

    enum class ViewType(val type: Int) {
        TYPE_ONE(0),
        TYPE_TWO(1)
    }
}

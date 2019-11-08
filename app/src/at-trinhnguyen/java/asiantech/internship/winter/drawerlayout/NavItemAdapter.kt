package asiantech.internship.winter.drawerlayout

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R

class NavItemAdapter(private val mNavItems: MutableList<NavItem?>, private val mContext: Context) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_ITEM = 1
        private const val REQUEST_IMAGE_CAPTURE = 111
        private const val REQUEST_GET_CONTENT_IMAGE = 222
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_HEADER) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nav_header, parent, false)
            HeaderViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nav, parent, false)
            NavItemViewHolder(view)
        }
    }

    override fun getItemCount() = mNavItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NavItemViewHolder) {
            holder.onBind(position)
        } else if (holder is HeaderViewHolder) {
            holder.onBind()
        }
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind() {
            itemView.findViewById<ImageView>(R.id.imgAvatar).setOnClickListener {
                AlertDialog.Builder(mContext).apply {
                    setTitle("Choose Action ")
                    setMessage("Take photo from camera or gallery?")

                    setPositiveButton("Camera") { _, _ ->
                        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                            takePictureIntent.resolveActivity(mContext.packageManager)?.also {
                                (mContext as android.app.Activity).startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                            }
                        }
                    }

                    setNegativeButton("Gallery") { _, _ ->
                        val intent = Intent(Intent.ACTION_GET_CONTENT)
                        intent.type = "image/*"
                        intent.resolveActivity(mContext.packageManager)?.also {
                            (mContext as android.app.Activity).startActivityForResult(intent, REQUEST_GET_CONTENT_IMAGE)
                        }
                    }

                    setNeutralButton("Cancel") { _, _ ->
                    }
                }.create().show()
            }
        }
    }

    inner class NavItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {
            val navItem = mNavItems[position]

            val icNavItem = itemView.findViewById<ImageView>(R.id.icNavItem)
            val tvNavItem = itemView.findViewById<TextView>(R.id.tvNavItem)

            navItem?.also {
                icNavItem.setImageResource(it.icon)
                tvNavItem.text = it.title
            }

            itemView.findViewById<ConstraintLayout>(R.id.llNavItem).setOnClickListener {
                Toast.makeText(mContext, "${navItem?.title} is clicked", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_HEADER
        else VIEW_TYPE_ITEM
    }
}

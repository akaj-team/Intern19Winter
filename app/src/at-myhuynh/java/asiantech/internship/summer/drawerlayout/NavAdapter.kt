package asiantech.internship.summer.drawerlayout

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R

class NavItemAdapter(private val mNavItems: MutableList<NavItem>, private val mHeader: Header, private val mContext: Context) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.TYPE_ONE.type -> {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.row_item_header, parent, false)
                HeaderHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.row_item_drawer, parent, false)
                NavItemHolder(view)
            }
        }
    }

    override fun getItemCount() = mNavItems.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.apply {
            when (holder) {
                is HeaderHolder -> holder.onBindData()
                is NavItemHolder -> holder.onBindData(position - 1)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> ViewType.TYPE_ONE.type
            else -> ViewType.TYPE_TWO.type
        }
    }

    inner class HeaderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBindData() {
            val imgAvatar = itemView.findViewById<ImageView>(R.id.imgAvatar)
            val spinnerEmail = itemView.findViewById<Spinner>(R.id.spinnerEmail)
            val listItems = mutableListOf(mHeader.mEmail)
            val spinner = ArrayAdapter(mContext, R.layout.support_simple_spinner_dropdown_item, listItems)
            spinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)

            imgAvatar.setImageResource(mHeader.mAvatar)
            spinnerEmail.adapter = spinner

            imgAvatar.setOnClickListener {
                dispatchTakePictureIntent(mContext)
            }
        }
    }

    private fun dispatchTakePictureIntent(context: Context) {
        val options = arrayOf("Take Photo", "Choose from Gallery", "Cancel")

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Choose your profile picture")

        builder.setItems(options) { dialog, item ->
            when {
                options[item] == "Take Photo" -> {
                    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                        takePictureIntent.resolveActivity(mContext.packageManager)?.also {
                            (mContext as Activity).startActivityForResult(takePictureIntent, 0)
                        }
                    }
                }

                options[item] == "Choose from Gallery" -> {
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).also { takePictureIntent ->
                        takePictureIntent.resolveActivity(mContext.packageManager)?.also {
                            (mContext as Activity).startActivityForResult(takePictureIntent, 1)
                        }
                    }
                }
                options[item] == "Cancel" -> dialog.dismiss()
            }
        }
        builder.show()
    }

    inner class NavItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBindData(position: Int) {
            val imgIcon = itemView.findViewById<ImageView>(R.id.imgIcon)
            val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
            val navItem = mNavItems[position]

            imgIcon.setImageResource(navItem.mIcon)
            tvTitle.text = navItem.mTitle

            itemView.setOnClickListener {
                Toast.makeText(mContext, navItem.mTitle, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

enum class ViewType(val type: Int) {
    TYPE_ONE(0),
    TYPE_TWO(1)
}

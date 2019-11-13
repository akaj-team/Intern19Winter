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

class DrawerLayoutAdapter(private val itemHeader: MutableList<DrawerLayoutHeaderItem>, private val mContext: Context, private val itemBody: MutableList<DrawerLayoutBodyItem>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewType(val type: Int) {
        TYPE_ONE(0), TYPE_TWO(1)
    }

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 100
        const val REQUEST_IMAGE_GALLERY = 101
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.TYPE_ONE.type -> {
                val view =
                        LayoutInflater.from(parent.context).inflate(R.layout.row_header_drawer, parent, false)
                HeaderItemViewHolder(view)
            }
            else -> {
                val view =
                        LayoutInflater.from(parent.context).inflate(R.layout.row_body_drawer, parent, false)
                BodyItemViewHolder(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            in 0 until itemHeader.size -> ViewType.TYPE_ONE.type
            else -> ViewType.TYPE_TWO.type
        }
    }

    override fun getItemCount(): Int {
        return itemBody.size + itemHeader.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.apply {
            when (holder) {
                is HeaderItemViewHolder -> holder.onBinData(position)
                is BodyItemViewHolder -> holder.onBinData(position - (itemHeader.size))
            }
        }
    }

    inner class HeaderItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgAvatar: ImageView = itemView.findViewById(R.id.imgAvatar)
        private val spnEmail: Spinner = itemView.findViewById(R.id.spinnerEmail)
        fun onBinData(position: Int) {
            val headerItem = itemHeader[position]
            imgAvatar.setImageResource(headerItem.avatar)

            //adapter cho Spinner
            val list = mutableListOf(headerItem.email)
            val spinner = ArrayAdapter(mContext, R.layout.support_simple_spinner_dropdown_item, list)
            spinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            spnEmail.adapter = spinner

            imgAvatar.setOnClickListener {
                showPictureDialog()
            }
        }

        private fun takePhotoFromCamera() {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(mContext.packageManager)?.also {
                    (mContext as Activity).startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }

        private fun choosePhotoFromGallery() {
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).also { takePictureIntent ->
                takePictureIntent.resolveActivity(mContext.packageManager)?.also {
                    (mContext as Activity).startActivityForResult(takePictureIntent, REQUEST_IMAGE_GALLERY)
                }
            }
        }

        private fun showPictureDialog() {
            val pictureDialog = AlertDialog.Builder(mContext)
            pictureDialog.setTitle("Select Action")
            val pictureDialogItem = arrayOf("Open your camera", "Take from gallery")
            pictureDialog.setItems(pictureDialogItem) { _, which ->
                when (which) {
                    0 -> takePhotoFromCamera()
                    1 -> choosePhotoFromGallery()
                }
            }
            pictureDialog.show()
        }
    }

    inner class BodyItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgIcon: ImageView = itemView.findViewById(R.id.imgIcon)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        fun onBinData(position: Int) {
            val bodyItem = itemBody[position]
            imgIcon.setImageResource(bodyItem.icon)
            tvTitle.text = bodyItem.title
            itemView.setOnClickListener {
                Toast.makeText(mContext, bodyItem.title, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

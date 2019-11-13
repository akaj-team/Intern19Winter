package asiantech.internship.summer.drawerlayout

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-nhatnguyen`.activity_drawer_layout.*
import kotlinx.android.synthetic.`at-nhatnguyen`.row_header_drawer.*

class DrawerLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer_layout)

        val itemHeader = DrawerLayoutHeaderItem(R.drawable.img_man, "nhatnguyencit@gmail.com")
        val item = mutableListOf(itemHeader)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val listBodyItem = mutableListOf<DrawerLayoutBodyItem>()
        listBodyItem.apply {
            add(DrawerLayoutBodyItem(R.drawable.ic_move_to_inbox_black, "Inbox"))
            add(DrawerLayoutBodyItem(R.drawable.ic_outbox_black, "Outbox"))
            add(DrawerLayoutBodyItem(R.drawable.ic_trash_black, "Trash"))
            add(DrawerLayoutBodyItem(R.drawable.ic_spam_black, "Spam"))
        }
        val adapter = DrawerLayoutAdapter(item, this, listBodyItem)
        recyclerView.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == DrawerLayoutAdapter.REQUEST_IMAGE_GALLERY && resultCode == Activity.RESULT_OK) {
            imgAvatar.setImageURI(data?.data)
        } else if (requestCode == DrawerLayoutAdapter.REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imgBitMap = data?.extras?.get("data") as Bitmap
            imgAvatar.setImageBitmap(imgBitMap)
        }
    }
}

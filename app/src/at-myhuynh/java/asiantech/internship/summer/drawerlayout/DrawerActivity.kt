package asiantech.internship.summer.drawerlayout

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-myhuynh`.activity_drawer.*
import kotlinx.android.synthetic.`at-myhuynh`.row_item_header.*

class DrawerActivity : AppCompatActivity() {
    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
        val header = Header(R.drawable.ic_man, "huynhvanmy028@gmail.com")
        val navItems = mutableListOf<NavItem>()
        navItems.apply {
            add(NavItem(R.drawable.ic_move_to_inbox_black_24dp, "Inbox"))
            add(NavItem(R.drawable.ic_send_black_24dp, "Outbox"))
            add(NavItem(R.drawable.ic_delete_black_24dp, "Trash"))
            add(NavItem(R.drawable.ic_info_black_24dp, "Spam"))
        }

        val navItemAdapter = NavItemAdapter(navItems, header, this)

        rvDrawer.layoutManager = LinearLayoutManager(this)
        rvDrawer.adapter = navItemAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    imgAvatar.setImageBitmap(imageBitmap)
                }
                else -> imgAvatar.setImageURI(data?.data)
            }
        }
    }
}

package asiantech.internship.summer.drawerlayout

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.`at-vinhnguyen`.activity_drawer.*

class DrawerActivity : AppCompatActivity(),RecyclerViewClickListener{

    private lateinit var imageProfile : CircleImageView

    override fun onImageClicked(imageView: CircleImageView) {
        imageProfile = imageView
        dispatchTakePictureIntent()
    }

    companion object{
        const val REQUEST_IMAGE_CAPTURE = 1
    }
    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageProfile.setImageBitmap(imageBitmap)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
        setSupportActionBar(toolbar)
        val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        initView()
    }

    private fun initView() {
        val listItemNav = mutableListOf(null,ItemNav(R.drawable.ic_move_to_inbox_black_24dp, "Inbox"),
                ItemNav(R.drawable.ic_send_black_24dp, "Outbox"),
                ItemNav(R.drawable.ic_delete_black_24dp, "Trash"),
                ItemNav(R.drawable.ic_info_black_24dp, "Spam"))
        val adapter = NavAdapter(listItemNav, applicationContext,this )
        recyclerViewNav.setHasFixedSize(true)
        recyclerViewNav.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        recyclerViewNav.adapter = adapter
    }


    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
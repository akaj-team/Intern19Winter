package asiantech.internship.summer.drawerlayout

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.`at-tamle`.activity_drawerlayout.*


import android.widget.Toast
import android.content.Intent
import asiantech.internship.summer.R
import com.theartofdev.edmodo.cropper.CropImage

class DrawerlayoutActivity : AppCompatActivity(), View.OnClickListener {
    var drawerLayout: DrawerLayout? = null
    private var menuItems: List<MenuItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawerlayout)

        initView()
        actionBarVsDrawerLayout()
    }

    private fun actionBarVsDrawerLayout() {
        drawerLayout?.setScrimColor(Color.TRANSPARENT)
        setSupportActionBar(toolbar)    // hamburger button
        val hamburger = object : ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawerlayout_open, R.string.drawerlayout_close) {

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                linearLayoutContent.setTranslationX(drawerView.getWidth() * slideOffset)
            }
        }
        drawerLayout?.addDrawerListener(hamburger)
        hamburger.syncState()
        supportActionBar!!.setTitle(getString(R.string.toolbar_title_home))
    }

    private fun initView() {
        drawerLayout = findViewById(R.id.drawerLayout)
        imgPhoto.setOnClickListener(this)

        menuItems = MenuItem.getItems()
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(this, linearLayoutManager.orientation))
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = MenuItemAdapter(this, menuItems!!)
    }

    override fun onClick(view: View?) {
        takeImageFromCameraOrGallery()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                imgPhoto.setImageURI(result.uri)
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, getString(R.string.toast_canceled), Toast.LENGTH_SHORT).show()
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, getString(R.string.toast_error), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, getString(R.string.toast_error), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun takeImageFromCameraOrGallery() {
        CropImage.activity().start(this@DrawerlayoutActivity)
    }
}

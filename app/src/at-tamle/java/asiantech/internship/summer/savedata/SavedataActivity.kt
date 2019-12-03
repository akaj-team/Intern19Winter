package asiantech.internship.summer.savedata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import asiantech.internship.summer.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.`at-tamle`.fragment_savedata.*
import kotlinx.android.synthetic.`at-tamle`.toolbar.*


class SavedataActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_savedata)

        supportFragmentManager.beginTransaction()
                .replace(R.id.framelayout, ContentFragment())
                .commit()
        initView()
        initActionBar()
    }

    private fun initView() {
        navigationview.setNavigationItemSelectedListener(this)
    }

    private fun initActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(getString(R.string.home_page))

        val hamburger = ActionBarDrawerToggle(this, drawlayout, toolbar, R.string.open, R.string.close)
        drawlayout.addDrawerListener(hamburger)
        hamburger.syncState()
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.menuHome -> {
                supportFragmentManager.beginTransaction().replace(R.id.framelayout, ContentFragment()).commit()
                supportActionBar?.setTitle(getString(R.string.home_page))
            }
            R.id.menuProfile -> {

            }
            R.id.menuAdd -> {
                supportFragmentManager.beginTransaction().replace(R.id.framelayout, AddTodoFragment()).commit()
                supportActionBar?.setTitle(getString(R.string.add_todo))
            }
            R.id.menuLogout -> {
                Preference(this).clear()
                startActivity(Intent(this, LoginRegisterActivity::class.java))
            }
        }
        drawlayout.closeDrawer(GravityCompat.START)
        return true
    }
}

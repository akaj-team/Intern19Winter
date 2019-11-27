package asiantech.internship.summer.viewpagertablayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-vinhnguyen`.activity_tablayout.*

class TabLayoutActivity : AppCompatActivity() {

    private lateinit var listTabLayoutFragment: MutableList<FragmentViewPagerObject>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tablayout)
        initData()
        initAdapter()
    }

    private fun initAdapter() {
        val adapter = ViewPagerAdapter(supportFragmentManager, listTabLayoutFragment)
        viewPagerTabLayout.adapter = adapter
        tabLayout.setupWithViewPager(viewPagerTabLayout)
    }

    private fun initData() {
        val listImage = mutableListOf(R.drawable.tablayout1, R.drawable.tablayout2, R.drawable.tablayout3, R.drawable.tablayout4, R.drawable.tablayout5, R.drawable.tablayout6)
        listImage.shuffle()
        listTabLayoutFragment = arrayListOf(FragmentViewPagerObject(TabFragment.newInstance(listImage[0]), "HOME"),
                FragmentViewPagerObject(TabFragment.newInstance(listImage[1]), "INFO"),
                FragmentViewPagerObject(TabFragment.newInstance(listImage[2]), "ANOTHER"))
    }
}

package asiantech.internship.summer.tablayout

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R


import kotlinx.android.synthetic.`at-tamle`.activity_first.*
import kotlinx.android.synthetic.`at-tamle`.activity_first.viewPager


class FirstActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        initView()
    }


    private fun initView() {
        tvNext.setOnClickListener(this)
        val fragments = mutableListOf<Fragment>()
        fragments.add(Step1Fragment())
        fragments.add(Step2Fragment())
        fragments.add(Step3Fragment())

        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, fragments, listOf("", "", ""))
        tabLayoutone.setupWithViewPager(viewPager)
        viewPager.setCurrentItem(0)

    }


//    private fun initView() {
//        tvNext.setOnClickListener(this)
//        val fragments: MutableList<Fragment> = mutableListOf()
//        fragments.add(Step1Fragment())
//        fragments.add(Step2Fragment())
//        fragments.add(Step3Fragment())
//
//        viewPager?.adapter = ViewPagerAdapter(supportFragmentManager, fragments)
//        circleIndicator.setViewPager(viewPager)
//
//
//    }

    override fun onClick(view: View?) {
        val position = viewPager!!.currentItem + 1
        if (position < 3) {
            viewPager?.setCurrentItem(position, true)
        } else {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

}

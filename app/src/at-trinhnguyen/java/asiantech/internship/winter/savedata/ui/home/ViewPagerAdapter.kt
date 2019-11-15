package asiantech.internship.winter.savedata.ui.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import asiantech.internship.summer.R
import asiantech.internship.winter.savedata.ui.done.DoneFragment
import asiantech.internship.winter.savedata.ui.todo.TodoFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, private val mContext: Context) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TodoFragment()
            else -> DoneFragment()
        }
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> mContext.getString(R.string.tablayout_title_todo)
            else -> mContext.getString(R.string.tablayout_title_done)
        }
    }
}

package asiantech.internship.winter.savedata.ui.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import asiantech.internship.summer.R
import asiantech.internship.winter.savedata.ui.done.DoneFragment
import asiantech.internship.winter.savedata.ui.todo.TodoFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, private val context: Context) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TodoFragment.newInstance()
            else -> DoneFragment.newInstance()
        }
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.getString(R.string.tablayout_title_todo)
            else -> context.getString(R.string.tablayout_title_done)
        }
    }
}

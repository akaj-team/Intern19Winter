package asiantech.internship.summer.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class TabLayoutAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 ->{
                FirstTabFragment()
            }
            1->{
                SecondTabFragment()
            }
            else->{
                ThirdTabFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0-> "Tab 1"
            1->"Tab 2"
            else -> "Tab 3"
        }
    }
}

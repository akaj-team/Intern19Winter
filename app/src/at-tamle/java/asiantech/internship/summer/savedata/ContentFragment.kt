package asiantech.internship.summer.savedata

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-tamle`.fragment_tablayout.*

class ContentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tablayout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as SavedataActivity).supportActionBar?.setTitle(getString(R.string.home_page))
        val preference: Preference = Preference(activity as SavedataActivity)
        if (preference.getInt(Preference.ID_USER) == 0) {
            startActivity(Intent(activity, LoginRegisterActivity::class.java))
        }

        initView()
    }

    private fun initView() {
        val fragments = mutableListOf<Fragment>()
        fragments.add(TodoFragment())
        fragments.add(DoneFragment())
        viewPager.adapter = ViewPagerAdapter(fragmentManager!!, fragments, listOf(R.string.todo,R.string.done))
        tablayout.setupWithViewPager(viewPager)
    }
}
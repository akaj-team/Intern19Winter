package asiantech.internship.summer.savedatabase.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.R
import asiantech.internship.summer.savedatabase.adapter.DrawerAdapter
import asiantech.internship.summer.savedatabase.adapter.TabLayoutAdapter
import asiantech.internship.summer.savedatabase.items.ItemDrawerBody
import asiantech.internship.summer.savedatabase.items.ItemDrawerHeader
import asiantech.internship.summer.savedatabase.items.ItemOnclick
import asiantech.internship.summer.savedatabase.items.ListFragment
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_viewpager.*

class ViewPagerFragment : Fragment() {

    companion object {
        private const val ARG_USER_ID ="userID"
        fun newInstance(userId: Int) = ViewPagerFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_USER_ID,userId)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_viewpager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userID = arguments?.getInt(ARG_USER_ID)

        val listFragment = mutableListOf<ListFragment>()
        listFragment.apply {
            add(ListFragment(ItemToDoFragment.newInstance(userID!!), getString(R.string.todo)))
            add(ListFragment(DoneFragment(), getString(R.string.done)))
        }
        val viewPagerFragment = fragmentManager?.let { TabLayoutAdapter(it, listFragment) }

        viewPager.adapter = viewPagerFragment
        tabLayout.setupWithViewPager(viewPager)

        val listHeader = ItemDrawerHeader(R.drawable.ic_man, "nnnn")
        val items = mutableListOf(listHeader)

        val listItemBody = mutableListOf<ItemDrawerBody>()
        listItemBody.add(ItemDrawerBody(R.drawable.ic_mode_edit_black, getString(R.string.text_edit_profile)))
        listItemBody.add(ItemDrawerBody(R.drawable.ic_logout_black, getString(R.string.text_log_out)))


        recyclerView.layoutManager = LinearLayoutManager(activity)
        val adapter = context?.let { DrawerAdapter(items, listItemBody) }
        recyclerView.adapter = adapter

        adapter?.let { setOnClickDrawerBody(it) }
    }

    private fun setOnClickDrawerBody(adapter: DrawerAdapter) {
        adapter.onClick(object : ItemOnclick {
            override fun onclick(item: ItemDrawerBody) {
                if (item.mTitle == "Log out") {
                    fragmentManager?.beginTransaction()?.replace(R.id.frameLayoutActivity, LoginFragment.newInstance("",""))?.commit()
                } else {
                    fragmentManager?.beginTransaction()?.replace(R.id.frameLayoutActivity, EditProfileFragment.newInstance())?.commit()
                }
            }
        })
    }
}

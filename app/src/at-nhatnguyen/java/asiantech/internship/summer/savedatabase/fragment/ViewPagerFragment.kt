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

        fun newInstance() = ViewPagerFragment()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_viewpager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val listFragment = mutableListOf<ListFragment>()
        listFragment.apply {
            add(ListFragment(ItemToDoFragment(), getString(R.string.todo)))
            add(ListFragment(DoneFragment(), getString(R.string.done)))
        }
        val viewPagerFragment = fragmentManager?.let {
            TabLayoutAdapter(it, listFragment)
        }
        viewPager.adapter = viewPagerFragment
        tabLayout.setupWithViewPager(viewPager)

        val listHeader = ItemDrawerHeader(R.drawable.ic_man, "nnnn")
        val items = mutableListOf(listHeader)

        val listItemBody = mutableListOf<ItemDrawerBody>()
        listItemBody.add(ItemDrawerBody(R.drawable.ic_mode_edit_black, "Edit profile"))
        listItemBody.add(ItemDrawerBody(R.drawable.ic_logout_black, "Log out"))


        recyclerView.layoutManager = LinearLayoutManager(activity)
        val adapter = context?.let { DrawerAdapter(items, listItemBody) }
        recyclerView.adapter = adapter
        adapter?.let { setOnClickDrawerBody(it) }
    }

    private fun setOnClickDrawerBody(adapter: DrawerAdapter) {
        adapter.onClick(object : ItemOnclick {
            override fun onclick(item: ItemDrawerBody) {
                if (item.mTitle == "Log out") {
                    fragmentManager?.beginTransaction()?.replace(R.id.frameLayout, LoginFragment.newInstance("", ""))?.commit()
                } else {
                    fragmentManager?.beginTransaction()?.replace(R.id.frameLayout, EditProfileFragment.newInstance())?.commit()
                }
            }
        })
    }
}

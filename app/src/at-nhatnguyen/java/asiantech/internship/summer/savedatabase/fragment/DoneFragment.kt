package asiantech.internship.summer.savedatabase.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.R
import asiantech.internship.summer.savedatabase.adapter.RowDoneAdapter
import asiantech.internship.summer.savedatabase.items.ItemDone
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_done.*

class DoneFragment : Fragment() {

    companion object {
        fun newInstance() = DoneFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_done, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemDone = mutableListOf<ItemDone>()
        for (i in 1..6) {
            itemDone.add(ItemDone("Done $i"))
        }
        val adapter = RowDoneAdapter(itemDone)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }
}
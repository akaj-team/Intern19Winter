package asiantech.internship.summer.savedatabase.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.R
import asiantech.internship.summer.savedatabase.adapter.RowTodoAdapter
import asiantech.internship.summer.savedatabase.database.DBHandling
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_item_todo.*

class ItemToDoFragment : Fragment() {

    companion object {
        fun newInstance() = ItemToDoFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_item_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val todoHandling = DBHandling(requireContext())
        val iTemTodo = todoHandling.readTodo()
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val adapter = RowTodoAdapter(iTemTodo)
        recyclerView.adapter = adapter

        floatingActionButton.setOnClickListener {
            fragmentManager?.beginTransaction()?.
                    replace(R.id.frameLayout,NewTodoFragment.newInstance())?.
                    commit()
        }
    }
}

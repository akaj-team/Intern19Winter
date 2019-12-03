package asiantech.internship.summer.savedata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-tamle`.fragment_todo.*

class TodoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idUser = activity?.let { Preference(it).getInt(Preference.ID_USER) }
        if (idUser != 0) {
            val todoDAO = activity?.let { Todo(it) }

            val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            recyclerViewTodo.setHasFixedSize(true)
            recyclerViewTodo.addItemDecoration(DividerItemDecoration(context!!, layoutManager.orientation))
            recyclerViewTodo.layoutManager = layoutManager
            recyclerViewTodo.adapter = TodoAdapter(activity!!, todoDAO!!.getlist(idUser!!, 0))
        }
    }
}

package asiantech.internship.winter.savedata.ui.done

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import asiantech.internship.summer.R
import asiantech.internship.summer.databinding.FragmentDoneBinding
import asiantech.internship.winter.savedata.db.todo.Todo
import kotlinx.android.synthetic.`at-trinhnguyen`.fragment_todo.*


class DoneFragment : Fragment() {

    companion object {
        fun newInstance() = DoneFragment()
    }

    private lateinit var viewModel: DoneViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentDoneBinding>(inflater, R.layout.fragment_done, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val todos = mutableListOf<Todo>().apply {
            (1..20).forEach {
                add(Todo(it.toLong(), 111, "Title $it", "Description $it", it % 2 == 0))
            }
        }
        val adapter = context?.let { DoneAdapter(it) }
        adapter?.setTodos(todos.filter { it.isCompleted })
        recyclerView.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DoneViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

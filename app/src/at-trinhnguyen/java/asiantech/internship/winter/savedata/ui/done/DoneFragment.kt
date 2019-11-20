package asiantech.internship.winter.savedata.ui.done

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import asiantech.internship.summer.R
import asiantech.internship.summer.databinding.FragmentDoneBinding
import asiantech.internship.winter.savedata.db.TodoDatabase
import asiantech.internship.winter.savedata.ui.ViewModelFactory


class DoneFragment : Fragment() {

    private lateinit var binding: FragmentDoneBinding
    private var idUser = 0L

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_done, container, false)

        val application = requireNotNull(activity?.application)
        val dataSource = TodoDatabase.getInstance(application)
        val viewModelFactory = ViewModelFactory(dataSource, application)
        val doneViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(DoneViewModel::class.java)
        binding.doneViewModel = doneViewModel

        activity?.getPreferences(Context.MODE_PRIVATE)?.apply {
            idUser = getLong(getString(R.string.pref_id_user), 0L)
        }

        val adapter = context?.let { DoneAdapter(it) }
        doneViewModel.getDonesByIdUser(idUser).observe(viewLifecycleOwner, Observer {
            it?.let { adapter?.setTodos(it) }
        })
        binding.recyclerView.adapter = adapter
        binding.lifecycleOwner = this

        return binding.root
    }
}

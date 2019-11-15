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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DoneViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

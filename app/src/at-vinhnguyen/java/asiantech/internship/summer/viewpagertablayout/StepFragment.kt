package asiantech.internship.summer.viewpagertablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R

class StepFragment : Fragment() {
    companion object {

        private const val STEP_NAME: String = "step_name"

        fun newInstance(stepName: String): Fragment {
            val bundle = Bundle()
            bundle.putString(STEP_NAME, stepName)
            val stepFragment = StepFragment()
            stepFragment.arguments = bundle
            return stepFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_step, container, false)
        val textViewStep = view.findViewById<TextView>(R.id.tvStep)
        textViewStep.text = arguments?.getString(STEP_NAME)
        return view
    }
}

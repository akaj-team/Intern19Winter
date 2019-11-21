package asiantech.internship.winter.savedata.ui


import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import asiantech.internship.summer.R
import asiantech.internship.summer.databinding.FragmentNewTodoBinding


class NewTodoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentNewTodoBinding>(inflater, R.layout.fragment_new_todo, container, false)
        binding.btnAdd.setOnClickListener {
            when {
                TextUtils.isEmpty(binding.edtTitle.text) -> {
                    Toast.makeText(context, "Please enter todo title!", Toast.LENGTH_LONG).show()
                }
                TextUtils.isEmpty(binding.edtDescription.text) -> {
                    Toast.makeText(context, "Please enter todo description!", Toast.LENGTH_LONG).show()
                }
                else -> {
                    it.findNavController().navigate(R.id.action_newTodoFragment_to_homeFragment, Bundle().apply {
                        putString(getString(R.string.args_title), binding.edtTitle.text.toString())
                        putString(getString(R.string.args_description), binding.edtDescription.text.toString())
                    })
                }
            }
        }

        return binding.root
    }
}

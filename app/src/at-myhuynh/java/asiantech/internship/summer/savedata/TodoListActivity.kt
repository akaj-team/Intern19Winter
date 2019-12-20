package asiantech.internship.summer.savedata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import asiantech.internship.summer.R
import asiantech.internship.summer.savedata.fragment.TodoListHomeFragment

class TodoListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        replaceFragment(TodoListHomeFragment.newInstance(), false)
    }

    fun replaceFragment(fragment: Fragment, isAddToBackStack: Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(fragment.javaClass.simpleName)
        }
        fragmentTransaction.replace(R.id.flTodoListFragment, fragment)
        fragmentTransaction.commit()
    }

    fun removeFragmentInBackStack(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.remove(fragment)
        supportFragmentManager.popBackStack()
    }
}

package asiantech.internship.winter.layouteditor

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val bundle = MutableLiveData<Bundle>()
}
